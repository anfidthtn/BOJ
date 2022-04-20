import sys
n = int(sys.stdin.readline())
m = int(sys.stdin.readline())
edge = [None] + [set() for _ in range(n)]

for _ in range(m):
    s, d = map(int, sys.stdin.readline().split())
    edge[s].add(d)
    edge[d].add(s)

isVisited = [None] + [False] * n
waiting = [1]
isVisited[1] = True

while len(waiting) > 0:
    now = waiting.pop()
    for next in edge[now]:
        if isVisited[next] is False:
            isVisited[next] = True
            waiting.append(next)

print(isVisited.count(True) - 1)