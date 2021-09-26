# %%
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from datetime import datetime, timedelta

# 테스트를 위한 매개변수 값 설정
prdInfo = [1, 2, 1, 7]
prdTime = datetime(2021, 9, 17, 13, 51, 57)
plan_qty = 10
specs = [40.0, 30.0, 0.0, 0.0, 5.0, 5.0, 0.0, 0.0]
tols = [0.1, 0.1, 0.001, 0.001, 0.1, 0.01, 0.05, 0.05]

# prdInfo로부터 각각의 변수값 구하기
plant_cd, line_cd, item_cd, worker_no = prdInfo
prdInfoData = np.tile(np.array(prdInfo), (plan_qty, 1))

# %%
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

# 생성된 column 확인
# print(serial_noData)
# print(prd_dtData)

#%%
#3.측정값(C:8)
#3-1.식전개
# 1)dim_x(가로길이)
dim_x = np.random.normal(specs[0], tols[0]/2.3, plan_qty)
#print(dim_x)

# 2)dim_y(세로길이)
dim_y = np.random.normal(specs[1], tols[1]/2.3, plan_qty)
#print(dim_y)

# 3)dim_h(가로면세로편차)
dim_h =np.random.normal(specs[2],tols[2]/2.2,plan_qty)
#print(dim_h)

# 4)dim_w(세로면가로편차)
dim_w =np.random.normal(specs[3],tols[3]/2.2,plan_qty)
# print(dim_w)

# 5)hole_x(홀가로길이)
hole_x =np.random.normal(specs[4],tols[4]/2.3,plan_qty)
# print(hole_x)

# 6)hole_y(홀세로길이)
hole_y =np.random.normal(specs[5],tols[5]/2.3,plan_qty)
# print(hole_y)

#7)hole_xc(홀가로중심)
hole_xc =np.random.normal(specs[6],tols[6]/2.4,plan_qty)
# print(hole_xc)

#8)hole_yc(홀세로중심)
hole_yc =np.random.normal(specs[7],tols[7]/2.5,plan_qty)
# print(hole_yc)
#*편차확인함수*
#u = np.where((hole_yc <= specs[7] + tols[7]) & (hole_yc >= specs[7] - tols[7]), hole_yc, 0)
#print(np.count_nonzero(u) / plan_qty *100) %로 결과 볼수 있음!!!!


# %%
#3-2.for문(간결 = 속도up)
# 8개 측정값 plan_qty개 생성하기
k=[2.3,2.3,2.2,2.2,2.3,2.3,2.4,2.5]

for i in range(8):
    temp = np.random.normal(specs[i], tols[i]/k[i], plan_qty)
    if i == 0:
        measuredData = temp
    else:
        measuredData = np.c_[measuredData, temp]

# print(measuredData.shape)

# %%
# 3-3.컬럼합치기
#z = np.c_[dim_x, dim_y, dim_x, dim_y, dim_h, dim_w, dim_h, dim_w ]
#print(z.shape)
# %%
#4.계산값(C:4)
# 1)str_x(가로직진도=h/x)
str_x = np.reshape(dim_h / dim_x, (plan_qty,1))

# 2)str_y(세로직진도=w/y)
str_y = np.reshape(dim_w / dim_y, (plan_qty,1))

# 3)hole_d(직경=max(x,y))
hole_d = np.reshape(np.maximum(hole_x, hole_y), (plan_qty,1))

# 4)hole_ratio(홀비율=y/x)
hole_ratio = np.reshape(hole_y / hole_x, (plan_qty,1))

computedData = np.concatenate((str_x, str_y, hole_d, hole_ratio), axis=1)
# print(computedData.shape)

# %%
productionData = np.concatenate((prdInfoData, serial_noData, measuredData, computedData,prd_dtData), axis=1)
# print(productionData)

# %%
df = pd.DataFrame(productionData)
df.columns = ['plant_cd', 'line_cd', 'item_cd', 'worker_no', 'serial_no',
              'dim_x', 'dim_y', 'dim_h', 'dim_w', 'hole_x', 'hole_y', 'hole_xc', 'hole_yc',
              'str_x', 'str_y', 'hole_d', 'hole_ratio', 'prd_dt']
df.to_csv('productionData.csv', index=False)

# %%
#def simulateData(prdInfo, prdTime, plan_qty, specs, tols):
#   return prdInfo,prdTime,plan_qty,specs,tols


