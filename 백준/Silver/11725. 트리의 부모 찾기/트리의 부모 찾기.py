import sys
n = int(input())

edge = [None] + [[] for _ in range(n)]

for _ in range(n - 1):
    s, d = map(int, sys.stdin.readline().split())
    edge[s].append(d)
    edge[d].append(s)

isVisited = [None] + [False] * n

isVisited[1] = 0
waiting = [1]
while len(waiting) > 0:
    now = waiting.pop()
    for next in edge[now]:
        if isVisited[next] is False:
            isVisited[next] = now
            waiting.append(next)

for i in range(2, n + 1):
    print(isVisited[i])