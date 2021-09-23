# %%
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from datetime import datetime, timedelta

# %%
prdInfo = ['p_1', 'l_2', 'item_1', 7]
prdTime = datetime(2021, 9, 17, 13, 51, 57)
print(prdTime, prdTime.strftime("%Y%m%d_%H%M%S"))

# 생산시간에 1분 더하기 테스트
prdTimeNext = prdTime + timedelta(minutes=1)
print(prdTimeNext, prdTimeNext.strftime("%Y%m%d_%H%M%S"))

# DB(생산지시 테이블)에서 계획수량(plan_qty) 가져와야 함!
plan_qty = 10

# DB(품목 테이블)에서 품목코드(item_cd)로 검색해서 해당 specs와 tols 가져와야 함!
specs = [40.0, 30.0, 0.0, 0.0, 5.0, 5.0, 0.0, 0.0]
tols = [0.1, 0.1, 0.001, 0.001, 0.1, 0.01, 0.05, 0.05]


# %%
# 제품일련번호 생성 테스트
plantNo = prdInfo[0].split('_')[1]
# print(plantNo)
lineNo = prdInfo[1].split('_')[1]
# print(lineNo)
serial_no = prdTime.strftime("%Y%m%d_%H%M%S") + '_' + plantNo + '_' + lineNo
# print(serial_no)

# dim_x, dim_y 생성 테스트
dim_x = np.random.normal(specs[0], tols[0]/2, plan_qty)
dim_y = np.random.normal(specs[1], tols[1]/2, plan_qty)
print(dim_x.shape, dim_y.shape)

# nd-array 만들기 테스트
z = np.c_[dim_x, dim_y, dim_x, dim_y]
print(z.shape)

# plt.plot(dim_x, '*')
# np.where((dim_x <= specs[0] + tols[0]) & (dim_x >= specs[0] - tols[0]), dim_x, 0)

# %%
# 8개 측정값 plan_qty개 생성하기
for i in range(8):
    temp = np.random.normal(specs[i], tols[i]/2, plan_qty)
    if i == 0:
        measured = temp
    else:
        measured = np.c_[measured, temp]

print(measured.shape)

# 8개의 측정값으로부터 4개의 계산값 생성하기
print(measured[:,3])


# %%
# Save a numpy array in a csv file
np.savetxt('productionData.csv', productionData,
            delimiter=",",
            header="plant_cd, line_cd, item_cd, worker_no, serial_no,\
                    dim_x, dim_y, dim_h, dim_w, hole_x, hole_y, hole_xc, hole_yc,\
                    str_x, str_y, hole_d, hole_ratio, prd_dt")
