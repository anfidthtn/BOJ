from collections import deque
import sys

direction = ((-1, 0, 0), (1, 0, 0), (0, -1, 0), (0, 1, 0), (0, 0, -1), (0, 0, 1))

while True:
    L, R, C = map(int, sys.stdin.readline().split())
    if L | R | C == 0:
        exit()
    Map = []
    Map.append([['#'] * (C + 2)] * (R + 2))
    for l in range(1, L + 1):
        tempL = []
        tempL.append(['#'] * (C + 2))
        for r in range(1, R + 1):
            tempL.append(['#'] + list(sys.stdin.readline().rstrip()) + ['#'])
            if 'S' in tempL[r]:
                S = (l, r, tempL[r].index('S'))
            if 'E' in tempL[r]:
                E = (l, r, tempL[r].index('E'))
        tempL.append(['#'] * (C + 2))

        Map.append(tempL)
        sys.stdin.readline()
    Map.append([['#'] * (C + 2)] * (R + 2))

    waiting = deque([[S, 0]])
    find = False
    while len(waiting) > 0:
        now = waiting.popleft()
        for dir in direction:
            nextL = now[0][0] + dir[0]
            nextR = now[0][1] + dir[1]
            nextC = now[0][2] + dir[2]
            if Map[nextL][nextR][nextC] == 'E':
                print('Escaped in ', now[1] + 1, ' minute(s).', sep='')
                find = True
                break
            elif Map[nextL][nextR][nextC] == '.':
                Map[nextL][nextR][nextC] = 'V'
                waiting.append([(nextL, nextR, nextC), now[1] + 1])
        if find is True:
            break
    if find is False:
        print('Trapped!')