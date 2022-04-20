import sys

N, M = map(int, input().split())

link = [None] + [[[],[], i] for i in range(1, N + 1)]

for _ in range(M):
    a, b = map(int, sys.stdin.readline().split())
    link[a][1].append(b)
    link[b][0].append(a)

waiting = []
for i in range(1, N + 1):
    if len(link[i][0]) == 0:
        waiting.append(link[i])

while len(waiting) > 0:
    out = waiting.pop()
    print(out[2], end=' ')
    for i in out[1]:
        link[i][0].remove(out[2])
        if len(link[i][0]) == 0:
            waiting.append(link[i])