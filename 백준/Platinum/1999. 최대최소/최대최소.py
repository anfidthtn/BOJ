import sys
from collections import deque


Mat = []

N, B, K = map(int, input().split())


for _ in range(N):
    tempL = []
    tempS = sys.stdin.readline().split()
    for s in tempS:
        tempL.append(int(s))
    Mat.append(tempL)

# def printttt(x):
#     for idx, i in enumerate(x):
#         print(idx)
#         if type(i) == list:
#             for idx2, j in enumerate(i):
#                 for k in range(idx2):
#                     print(' ', end='')
#                 print(j)
#         else:
#             print(i)
#     print()

# print(Mat,end='\n\n')

rowMinMax = []

for i in range(N):
    tempQ = [deque(), deque()] # min / max
    tempL =[[],[]]
    for j in range(N):
        while len(tempQ[0]) > 1 and tempQ[0][0] > Mat[i][j]:
            tempQ[0].popleft()
        while len(tempQ[1]) > 1 and tempQ[1][0] < Mat[i][j]:
            tempQ[1].popleft()

        for k in range(2):
            while len(tempQ[k]) < B:
                tempQ[k].appendleft(Mat[i][j])
        while tempQ[0][-1] > Mat[i][j]:
            tempQ[0].pop()
            tempQ[0].appendleft(Mat[i][j])
        while tempQ[1][-1] < Mat[i][j]:
            tempQ[1].pop()
            tempQ[1].appendleft(Mat[i][j])
        for k in range(2):
            if j >= B - 1:
                tempL[k].append(tempQ[k].pop())
            else:
                tempQ[k].pop()
    rowMinMax.append(tempL)

# printttt(rowMinMax)

rowcolMinMax = []
for _ in range(N - B + 1):
    rowcolMinMax.append([[],[]])

for j in range(N - B + 1):
    tempQ = [deque(), deque()]
    for i in range(N):
        while len(tempQ[0]) > 1 and tempQ[0][0] > rowMinMax[i][0][j]:
            tempQ[0].popleft()
        while len(tempQ[1]) > 1 and tempQ[1][0] < rowMinMax[i][1][j]:
            tempQ[1].popleft()

        for k in range(2):
            while len(tempQ[k]) < B :
                tempQ[k].appendleft(rowMinMax[i][k][j])
        while tempQ[0][-1] > rowMinMax[i][0][j]:
            tempQ[0].pop()
            tempQ[0].appendleft(rowMinMax[i][0][j])
        while tempQ[1][-1] < rowMinMax[i][1][j]:
            tempQ[1].pop()
            tempQ[1].appendleft(rowMinMax[i][1][j])
        for k in range(2):
            if i >= B - 1:
                rowcolMinMax[i - B + 1][k].append(tempQ[k].pop())
            else:
                tempQ[k].pop()

# printttt(rowcolMinMax)

for _ in range(K):
    r, c = map(int, sys.stdin.readline().split())
    print(rowcolMinMax[r-1][1][c-1] - rowcolMinMax[r-1][0][c-1])