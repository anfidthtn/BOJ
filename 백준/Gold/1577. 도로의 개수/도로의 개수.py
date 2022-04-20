n, m = map(int, input().split())
k = int(input())
state = [[3] * (n + 1) for _ in range(m + 1)]
for i in range(n + 1):
    state[0][i] &= (3 & (~ 1))
for i in range(m + 1):
    state[i][0] &= (3 & (~ 2))

for _ in range(k):
    a, b, c, d = map(int, input().split())
    if d - b == 1:
        state[d][c] &= (3 & (~ 1))
    if b - d == 1:
        state[b][a] &= (3 & (~ 1))
    if c - a == 1:
        state[d][c] &= (3 & (~ 2))
    if a - c == 1:
        state[b][a] &= (3 & (~ 2))

case = [[0] * (n + 1) for _ in range(m + 1)]
case[0][0] = 1
for i in range(m + 1):
    for j in range(n + 1):
        if state[i][j] & 1 > 0:
            case[i][j] += case[i - 1][j]
        if state[i][j] & 2 > 0:
            case[i][j] += case[i][j - 1]

print(case[m][n])