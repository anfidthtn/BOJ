import sys
n = int(sys.stdin.readline())
triangle = []
for _ in range(n):
    triangle.append(list(map(int, sys.stdin.readline().split())))

for i in range(n - 1, 0, -1):
    for j in range(i):
        triangle[i - 1][j] = triangle[i - 1][j] + max(triangle[i][j : j + 2])

print(triangle[0][0])