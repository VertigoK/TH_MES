import numpy as np
import pandas as pd
from datetime import datetime, timedelta
import csv
import MySQLdb
import argparse
import math

# Initialize parser.
parser = argparse.ArgumentParser()

# Add arguments.
parser.add_argument(
    "--prdInfo",
    nargs="+",
    type=int,
    default=[]
)
parser.add_argument(
    "--prdTime",
    nargs="+",
    type=str,
    default=[]
)
parser.add_argument(
    "--plan_qty",
    nargs="+",
    type=int,
    default=[]
)

# Parse the command line.
args = parser.parse_args()

# Define arguments.
prdInfo = args.prdInfo          # 생산지시번호, 공장코드, 라인코드, 품목코드, 근무자번호
prdTime = datetime.strptime(args.prdTime[0] + ' ' + args.prdTime[1].split('.')[0], '%Y-%m-%d %H:%M:%S')   # 첫 제품 생산시간
plan_qty = args.plan_qty[0]     # 계획 수량

item_cd = prdInfo[3]

if item_cd == 1:
    specs = [40.0, 30.0, 0.0, 0.0, 5.0, 5.0, 0.0, 0.0]      # 8개 측정값의 규격치
    tols = [0.1, 0.1, 0.001, 0.001, 0.1, 0.1, 0.05, 0.05]   # 8개 측정값의 허용치
    k = [2.7, 2.7, 2.6, 2.6, 2.7, 2.7, 2.8, 2.9]            # 불량률 조정 factor
    specs_measured = [0.0, 0.0, 5.0, 1.0]                   # 4개 계산값의 규격치
    tols_measured = [0.01, 0.01, 0.1, 0.1]                  # 4개 계산값의 허용치
elif item_cd == 2:
    specs = [30.0, 20.0, 0.0, 0.0, 3.0, 3.0, 0.0, 0.0]
    tols = [0.1, 0.1, 0.001, 0.001, 0.1, 0.1, 0.05, 0.05]
    k = [2.8, 2.8, 2.7, 2.6, 2.8, 2.9, 2.7, 2.8]
    specs_measured = [0.0, 0.0, 3.0, 1.0]
    tols_measured = [0.01, 0.01, 0.1, 0.1]
else:
    specs = [50.0, 40.0, 0.0, 0.0, 4.0, 4.0, 0.0, 0.0]
    tols = [0.1, 0.1, 0.001, 0.001, 0.1, 0.1, 0.05, 0.05]
    k = [2.7, 2.7, 2.7, 2.7, 2.7, 2.7, 2.7, 2.7]
    specs_measured = [0.0, 0.0, 4.0, 1.0]
    tols_measured = [0.01, 0.01, 0.1, 0.1]


# 생산정보/품질검사정보 데이터 생성 / 저장(CSV) / 업로드(DB) 함수 정의
def simulateData(prdInfo, prdTime, plan_qty, specs, tols, k, specs_measured, tols_measured):
    
    # 1. 생산정보 데이터 생성 및 저장

    # prdInfo로부터 각각의 변수값 구하기
    wo_no, plant_cd, line_cd, item_cd, worker_no = prdInfo
    prdInfoData = np.tile(np.array(prdInfo), (plan_qty, 1))

    # prd_dt를 저장할 빈 column 정의
    prd_dtData = np.empty([plan_qty, 1], dtype=object)

    # serial_no를 저장할 빈 column 정의
    serial_noData = np.empty([plan_qty, 1], dtype=object)

    # 메개변수로 받은 prdTime값으로부터 첫 번째 serial_no 생성
    serial_no = prdTime.strftime("%Y%m%d_%H%M%S") + '_' + str(plant_cd) + '_' + str(line_cd)

    # 빈 column에 값 하나씩 넣기 / 값 업데이트
    for i in range(plan_qty):
        # 빈 column에 값 하나씩 넣기
        prd_dtData[i] = prdTime
        serial_noData[i] = serial_no
        # 값 업데이트
        prdTime = prdTime + timedelta(minutes=1)
        serial_no = prdTime.strftime("%Y%m%d_%H%M%S") + '_' + str(plant_cd) + '_' + str(line_cd)

    # 8개의 생산정보 측정값(column) 생성 후 하니씩 합치기
    # dix_x, dim_y, dim_h, dim_w, hole_x, hole_y, hole_xc, hole_yc
    for i in range(8):
        measured = np.random.normal(specs[i], tols[i] / k[i], (plan_qty, 1))
        if i == 0:
            measuredData = measured
        else:
            measuredData = np.c_[measuredData, measured]

    # 4개의 생산정보 계산값(column) 생성 후 합치기
    # str_x, str_y, hole_d, hole_ratio
    str_x = np.reshape(measuredData[:,2] / measuredData[:,0], (plan_qty,1))             # 가로직진도 = hole_h / hole_x
    str_y = np.reshape(measuredData[:,3] / measuredData[:,1], (plan_qty,1))             # 세로직진도 = hole_w / hole_y
    hole_d = np.reshape(np.maximum(measuredData[:,4], measuredData[:,5]), (plan_qty,1)) # 직경 = max(hole_x, hole_y)
    hole_ratio = np.reshape(measuredData[:,5] / measuredData[:,4], (plan_qty,1))        # 홀비율 = hole_y / hole_x
    computedData = np.concatenate((str_x, str_y, hole_d, hole_ratio), axis=1)

    # 생산정보 데이터 모두 합치기
    productionData = np.concatenate((serial_noData, prdInfoData, measuredData, computedData, prd_dtData), axis=1)

    # 생산정보 데이터를 DataFrame으로 변환
    df = pd.DataFrame(productionData)
    df.columns = ['serial_no', 'wo_no', 'plant_cd', 'line_cd', 'item_cd', 'worker_no',
                  'dim_x', 'dim_y', 'dim_h', 'dim_w', 'hole_x', 'hole_y', 'hole_xc', 'hole_yc',
                  'str_x', 'str_y', 'hole_d', 'hole_ratio', 'prd_dt']

    # 생산시간 흐름에 따른 근무자번호(worker_no) 업데이트
    start_dt = df['prd_dt'].iloc[0]
    worker_line_cd = math.ceil(worker_no / 6)
    i = 1
    while (df['prd_dt'] - start_dt >= i * timedelta(hours=8)).any():
        if worker_no + 2 < worker_line_cd * 6:
            worker_no += 2
        else:
            worker_no += 2 - 6
        df.loc[df['prd_dt'] - start_dt >= i * timedelta(hours=8), 'worker_no'] = worker_no
        i += 1

    # 생산정보 데이터를 CSV로 저장
    file_name_production = 'C:\projects\TH_MES\DataGeneration\Data\production_' + serial_no + '.csv'
    df.to_csv(file_name_production, index=False)


    # 2. 품질검사정보 데이터 생성 및 저장

    # 품질검사에 사용할 8개의 규격치 재조합
    specs_qc = specs[:2] + specs[-2:] + specs_measured

    # 품질검사에 사용할 8개의 허용치 재조합
    tols_qc = tols[:2] + tols[-2:] + tols_measured

    # 품질검사에 사용할 4개의 측정값과 4개의 계산값 재조합
    data_qc = np.concatenate((measuredData[:,:2], measuredData[:,-2:], computedData), axis=1)
    
    # 품질검사 결과(8개의 검사결과와 1개의 검사종합결과)를 저장할 빈 numpy array 정의
    checkedData = np.empty([plan_qty, 9], dtype=int)

    # 품질검사 8개 항목 판정 (양품: 1, 불량품: 0)
    for i in range(8):
        checkedData[:,i] = np.where((data_qc[:,i] <= specs_qc[i] + tols_qc[i]) & (data_qc[:,i] >= specs_qc[i] - tols_qc[i]), 1, 0)
    
    # 8개의 검사결과를 종합한 검사종합결과를 9번째 column에 저장하기
    checkedData[:,8] = np.all(checkedData[:,:8], axis=1)

    # 품질검사정보 데이터 모두 합치기
    qualityData =np.concatenate((serial_noData, prdInfoData, checkedData), axis=1)

    # 품질검사정보 데이터를 DataFrame으로 변환
    df1 = pd.DataFrame(qualityData)
    df1.columns = ['serial_no', 'wo_no', 'plant_cd', 'line_cd', 'item_cd', 'worker_no',
                  'dimcheck_x', 'dimcheck_y', 'holecheck_xc', 'holecheck_yc',
                  'dimcheck_hx', 'dimcheck_wy', 'holecheck_d', 'holecheck_ratio', 'check_result']

        # 근무자번호 변경 (품질검사공정 근무자번호 = 생산공정 근무자번호 + 1)
    df1['worker_no'] = df['worker_no'] + 1

    # 품질검사정보 데이터를 CSV로 저장
    file_name_quality = 'C:\projects\TH_MES\DataGeneration\Data\quality_' + serial_no + '.csv'
    df1.to_csv(file_name_quality, index=False)


    # 3. 생산정보 데이터를 DB의 production 테이블에 업로드
    
    input_file = file_name_production
    file_reader = csv.reader(open(input_file, 'r'), delimiter=',')
    header = next(file_reader) # 첫 번째 줄 읽기

    conn = MySQLdb.connect(host='localhost', port=3306, db='mes', user='root', password='12345')
    cursor = conn.cursor()

    for row in file_reader:
        data = []
        for i  in range(len(header)):
            data.append(str(row[i].replace(',','')).strip())
        cursor.execute('insert into production values(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)', data)

    conn.commit()
    cursor.close()
    conn.close()


    # 4. 품질검사정보 데이터를 DB의 quality 테이블에 업로드

    input_file = file_name_quality
    file_reader = csv.reader(open(input_file, 'r'), delimiter=',')
    header = next(file_reader) # 첫 번째 줄 읽기

    conn = MySQLdb.connect(host='localhost', port=3306, db='mes', user='root', password='12345')
    cursor = conn.cursor()

    for row in file_reader:
        data = []
        for i  in range(len(header)):
            data.append(str(row[i].replace(',','')).strip())
        cursor.execute('insert into quality values(%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)', data)

    conn.commit()
    cursor.close()
    conn.close()


# 데이터 생성 함수 실행
simulateData(prdInfo, prdTime, plan_qty, specs, tols, k, specs_measured, tols_measured)