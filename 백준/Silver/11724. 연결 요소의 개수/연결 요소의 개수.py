import sys

n, m = map(int, sys.stdin.readline().split())

edge = [None] + [[] for _ in range(n)]

for _ in range(m):
    s, d = map(int, sys.stdin.readline().split())
    edge[s].append(d)
    edge[d].append(s)

isVisited = [None] + [False] * n

groupNum = 0
while isVisited.count(False) > 0:
    groupNum += 1
    idx = isVisited.index(False)
    waiting = [idx]
    isVisited[idx] = True
    while len(waiting) > 0:
        now = waiting.pop()
        for next in edge[now]:
            if isVisited[next] is False:
                isVisited[next] = True
                waiting.append(next)

print(groupNum)