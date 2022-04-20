import sys
from collections import deque

t = int(sys.stdin.readline())
U = (-1, 0)
D = (1, 0)
L = (0, -1)
R = (0, 1)
direction = (U, D, L, R)

for _ in range(t):
    row, col = map(int, sys.stdin.readline().split())
    Map = [list(sys.stdin.readline().rstrip()) for _ in range(row)]
    startingSpace = set()
    for r in range(row):
        for c in range(col):
            if Map[r][c] == '.':
                startingSpace.add((r, c))
            if Map[r][c] == 'O':
                escapePoint = (r, c)
    waiting = deque([[startingSpace, 0, []]])
    isFound = False
    while len(waiting) > 0:
        now = waiting.popleft()
        if isFound is True or now[1] >= 10:
            break
        for dir in direction:
            nextSpace = set()
            for nowSpace in now[0]:
                nsR = nowSpace[0]
                nsC = nowSpace[1]
                while Map[nsR + dir[0]][nsC + dir[1]] == '.':
                    nsR += dir[0]
                    nsC += dir[1]
                if Map[nsR + dir[0]][nsC + dir[1]] == '#':
                    nextSpace.add((nsR, nsC))
            if now[0] == nextSpace:
                continue
            nextWay = now[2].copy()
            nextWay.append(dir)
            if len(nextSpace) == 0:
                for way in nextWay:
                    if way == U:
                        print('U', end='')
                    if way == D:
                        print('D', end='')
                    if way == L:
                        print('L', end='')
                    if way == R:
                        print('R', end='')
                print()
                isFound = True
                break
            next = [nextSpace, now[1] + 1, nextWay]
            waiting.append(next)
    if isFound is False:
        print('XHAE')

