import math
n, k = map(int, input().split())
counts = [[0 for _ in range(7)] for _ in range(2)]
for _ in range(n):
    a, b = map(int, input().split())
    counts[a][b] += 1

ans = 0
ans += math.ceil((counts[1][1] + counts[1][2] + counts[0][1] + counts[0][2]) / k)
for i in range(2):
    s = 0
    for j in range(3, 7):
        s += counts[i][j]
    ans += math.ceil(s / k)
print(ans)