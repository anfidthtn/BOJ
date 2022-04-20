n = int(input())
data = [[0 for _ in range(201)] for _ in range(201)]
rank = [[0 for _ in range(201)] for _ in range(201)]
info = []
for _ in range(n):
    info.append(tuple(map(int, input().split())))
    data[info[-1][0]][info[-1][1]] += 1

for i in range(199, 9, -1):
    for j in range(199, 9, -1):
        rank[i][j] = rank[i + 1][j] + rank[i][j + 1] - rank[i + 1][j + 1] + data[i + 1][j + 1]

for i in range(n):
    print(rank[info[i][0]][info[i][1]] + 1, end=' ')