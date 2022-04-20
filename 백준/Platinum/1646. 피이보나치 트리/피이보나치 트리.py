from collections import deque

N, S, D = map(int, input().split())

piib = [1, 1]
for i in range(50):
    piib.append(1 + piib[-1] + piib[-2])

def findN(N, num, path):
    if num == 1:
        return
    num -= 1
    if num <= piib[N - 2]:
        path.append('L')
        findN(N - 2, num, path)
    else:
        num -= piib[N - 2]
        path.append('R')
        findN(N - 1, num, path)

path1 = []
findN(N, S, path1)
path2 = []
findN(N, D, path2)
path1 = deque(path1)
path2 = deque(path2)
while len(path1) > 0 and len(path2) > 0 and path1[0] == path2[0]:
    path1.popleft()
    path2.popleft()
while len(path1) > 0:
    print('U', end='')
    path1.pop()
while len(path2) > 0:
    print(path2.popleft(), end='')