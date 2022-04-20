import sys

P = int(input())

tCaseList = []
B = 0
M = 0
for _ in range(P):
    BM = list(map(int, sys.stdin.readline().split()))
    tCaseList.append(BM)
    if BM[0] > B:
        B = BM[0]
    if BM[1] > M:
        M = BM[1]

table = [[]]
table.append([0] + [i for i in range(1, M + 1)])
for b in range(2, B + 1):
    table.append([0, 1])
    for m in range(2, M + 1):
        table[b].append(10000)

        for now in range(1, m):
            temp = 1 + table[b][m - now]
            if temp < 1 + table[b - 1][now - 1]:
                temp = 1 + table[b - 1][now - 1]
            if table[b][m] > temp:
                table[b][m] = temp
            if table[b][m] == table[b][m - 1]:
                break
for [b, m] in tCaseList:
    print(table[b][m])


                

