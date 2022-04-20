import sys
from collections import deque

n, m, v = map(int, input().split())

edge = [None] + [[] for _ in range(n)]

for _ in range(m):
    a, b = map(int, sys.stdin.readline().split())
    edge[a].append(b)
    edge[b].append(a)

for i in range(1, n + 1):
    edge[i].sort()

isVisited = [None] + [False] * n
waiting = [v]
while len(waiting) > 0:
    now = waiting.pop()
    if isVisited[now] is True:
        continue
    isVisited[now] = True
    print(now, end=' ')
    for i in range(len(edge[now]) - 1, -1, -1):
        if isVisited[edge[now][i]] is False:
            waiting.append(edge[now][i])
print()
isVisited = [None] + [False] * n
waiting = deque([v])
isVisited[v] = True
while len(waiting) > 0:
    now = waiting.popleft()
    print(now, end=' ')
    for i in range(len(edge[now])):
        if isVisited[edge[now][i]] is False:
            waiting.append(edge[now][i])
            isVisited[edge[now][i]] = True