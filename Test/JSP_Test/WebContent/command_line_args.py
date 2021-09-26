# Command line arguments using argparse

import argparse
from datetime import datetime

# Initialize parser
parser = argparse.ArgumentParser()

# Add arguments
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

parser.add_argument(
    "--specs",
    nargs="+",
    type=float,
    default=[]
)

parser.add_argument(
    "--tols",
    nargs="+",
    type=float,
    default=[]
)

# Parse the command line
args = parser.parse_args()

# Access parser option
print("prdInfo: %r" % args.prdInfo)
# for i in range(len(args.prdInfo)):
#     print(args.specs[i])

print("prdTime: %r" % args.prdTime)
# for i in range(len(args.prdTime)):
#     print(args.specs[i])

print("plan_qty: %r" % args.plan_qty)
# for i in range(len(args.plan_qty)):
#     print(args.specs[i])

print("specs: %r" % args.specs)
# for i in range(len(args.specs)):
#     print(args.specs[i])

print("tols: %r" % args.tols)
# for i in range(len(args.tols)):
#     print(args.tols[i])

print(datetime.strptime(args.prdTime[0] + ' ' + args.prdTime[1], '%Y-%m-%d %H:%M:%S'))


#
#
# k = [2.3, 2.1, 2, ]
# def simProductionData(prdInfo, prdTime, plan_qty, specs, tols):
#     measured
#
# str_x = dim_h / dim_x
# str_y = dim_w / dim_y
# hole_d = max(hole_x, hole_y)
# print(hole_d.shape)
#
# # %%
# import numpy as np
# plan_qty = 100
#
# prdInfo = [1,2,1,7]
# prdInfoData = np.tile(np.array(prdInfo), (plan_qty, 1))
# print(prdInfoData)
# print(prdInfoData.shape)