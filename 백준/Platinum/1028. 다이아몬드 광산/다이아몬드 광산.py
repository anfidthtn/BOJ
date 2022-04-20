#1028
import sys

R, C = map(int, input().split())

Mine = []
for _ in range(R):
    tempS = sys.stdin.readline()
    tempL = []
    for i in range(C):
        tempL.append([int(tempS[i]), [0, 0, 0, 0]])
    Mine.append(tempL)

# [isDia, max[LU, RU, LD, RD]
LU = 0
RU = 1
LD = 2
RD = 3


# def TestPrintMine():
#     for i in Mine:
#         print(i)

for i in range(R):
    for j in range(C):
        if Mine[i][j][0] == 0:
            continue
        if i == 0 or j == 0:
            Mine[i][j][1][LU] = 1
        else:
            Mine[i][j][1][LU] = Mine[i - 1][j - 1][1][LU] + 1
        if i == 0 or j == C - 1:
            Mine[i][j][1][RU] = 1
        else:
            Mine[i][j][1][RU] = Mine[i - 1][j + 1][1][RU] + 1

for i in range(R).__reversed__():
    for j in range(C):
        if Mine[i][j][0] == 0:
            continue
        if i == R - 1 or j == 0:
            Mine[i][j][1][LD] = 1
        else:
            Mine[i][j][1][LD] = Mine[i + 1][j - 1][1][LD] + 1
        if i == R - 1 or j == C - 1:
            Mine[i][j][1][RD] = 1
        else:
            Mine[i][j][1][RD] = Mine[i + 1][j + 1][1][RD] + 1

# TestPrintMine()

max_dia = 0

def Min(a, b):
    if a > b:
        return b
    return a

for i in range(R):
    for j in range(C):
        if Mine[i][j][0] == 0:
            continue
        if Mine[i][j][1][LD] > max_dia and Mine[i][j][1][RD] > max_dia:
            tempMin = Min(Mine[i][j][1][LD], Mine[i][j][1][RD])
            for k in range(max_dia + 1, tempMin + 1).__reversed__():
                tempRow = i + (k - 1) * 2
                if tempRow >= R: # 탐색 범위 오버
                    continue
                if Min(Mine[tempRow][j][1][LU], Mine[tempRow][j][1][RU]) >= k:
                    max_dia = k
                    break

print(max_dia)