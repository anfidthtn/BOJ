import sys
n = int(sys.stdin.readline())
cost = []

MAXNUM = 10 ** 10

for i in range(n):
    cost.append(list(map(int, sys.stdin.readline().split())))

minCost = [[MAXNUM, MAXNUM, MAXNUM] for _ in range(n)]
minCost[0] = cost[0] # 어차피 얕은복사돼도 상관없음
for i in range(1, n):
    minCost[i][0] = cost[i][0] + min(minCost[i - 1][1], minCost[i - 1][2])
    minCost[i][1] = cost[i][1] + min(minCost[i - 1][2], minCost[i - 1][0])
    minCost[i][2] = cost[i][2] + min(minCost[i - 1][0], minCost[i - 1][1])

print(min(minCost[n - 1]))