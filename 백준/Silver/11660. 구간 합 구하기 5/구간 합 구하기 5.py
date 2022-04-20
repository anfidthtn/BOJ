import sys

N, M = map(int, sys.stdin.readline().split())

sumTable = [[0] * (N + 1)]
for _ in range(N):
    sumTable.append([0] + list(map(int, sys.stdin.readline().split())))

for row in range(1, N + 1):
    for col in range(1, N + 1):
        sumTable[row][col] += sumTable[row - 1][col] + sumTable[row][col - 1] - sumTable[row - 1][col - 1]

for _ in range(M):
    x1, y1, x2, y2 = map(int, sys.stdin.readline().split())
    print(sumTable[x2][y2] - sumTable[x2][y1 - 1] - sumTable[x1 - 1][y2] + sumTable[x1 - 1][y1 - 1])