from collections import deque

direction = [(0, 1), (0, -1), (-1, 0), (1, 0)]
R = 'R'
B = 'B'
Depth = 'Depth'
dirSym = 'dirSym'

N, M = map(int, input().split())
Map = []
for i in range(N):
    Map.append(list(input()))
    for j in range(M):
        if Map[i][j] == 'R':
            Rstart = [i, j]
            Map[i][j] = '.'
        if Map[i][j] == 'B':
            Bstart = [i, j]
            Map[i][j] = '.'

waiting = deque()
for dir in direction:
    waiting.append({'R' : Rstart.copy(), 'B' : Bstart.copy(), 'Depth' : 1, 'dirSym' : dir})

candidate = set()


while len(waiting) > 0:
    nowTry = waiting.popleft()
    originR = tuple(nowTry[R])
    originB = tuple(nowTry[B])
    Rgoal = False
    Bgoal = False
    while Map[nowTry[R][0] + nowTry[dirSym][0]][nowTry[R][1] + nowTry[dirSym][1]] != '#':
        if Map[nowTry[R][0] + nowTry[dirSym][0]][nowTry[R][1] + nowTry[dirSym][1]] == 'O':
            nowTry[R][0] = nowTry[R][0] + nowTry[dirSym][0]
            nowTry[R][1] = nowTry[R][1] + nowTry[dirSym][1]
            Rgoal = True
            break
        elif nowTry[R][0] + nowTry[dirSym][0] == nowTry[B][0] and nowTry[R][1] + nowTry[dirSym][1] == nowTry[B][1]:
            break
        else:
            nowTry[R][0] = nowTry[R][0] + nowTry[dirSym][0]
            nowTry[R][1] = nowTry[R][1] + nowTry[dirSym][1]
    while Map[nowTry[B][0] + nowTry[dirSym][0]][nowTry[B][1] + nowTry[dirSym][1]] != '#':
        if Map[nowTry[B][0] + nowTry[dirSym][0]][nowTry[B][1] + nowTry[dirSym][1]] == 'O':
            nowTry[B][0] = nowTry[B][0] + nowTry[dirSym][0]
            nowTry[B][1] = nowTry[B][1] + nowTry[dirSym][1]
            Bgoal = True
            break
        elif nowTry[B][0] + nowTry[dirSym][0] == nowTry[R][0] and nowTry[B][1] + nowTry[dirSym][1] == nowTry[R][1]:
            break
        else:
            nowTry[B][0] = nowTry[B][0] + nowTry[dirSym][0]
            nowTry[B][1] = nowTry[B][1] + nowTry[dirSym][1]
    while Map[nowTry[R][0] + nowTry[dirSym][0]][nowTry[R][1] + nowTry[dirSym][1]] != '#':
        if Map[nowTry[R][0] + nowTry[dirSym][0]][nowTry[R][1] + nowTry[dirSym][1]] == 'O':
            nowTry[R][0] = nowTry[R][0] + nowTry[dirSym][0]
            nowTry[R][1] = nowTry[R][1] + nowTry[dirSym][1]
            Rgoal = True
            break
        elif nowTry[R][0] + nowTry[dirSym][0] == nowTry[B][0] and nowTry[R][1] + nowTry[dirSym][1] == nowTry[B][1]:
            break
        else:
            nowTry[R][0] = nowTry[R][0] + nowTry[dirSym][0]
            nowTry[R][1] = nowTry[R][1] + nowTry[dirSym][1]
    if Bgoal is True:
        continue
    elif Rgoal is False:
        if tuple(nowTry[R]) == originR and tuple(nowTry[B]) == originB:
            continue
        if nowTry[Depth] == 10:
            continue
        if (tuple(nowTry[R]), tuple(nowTry[B])) in candidate:
            continue
        else:
            candidate.add((tuple(nowTry[R]), tuple(nowTry[B])))
        for dir in direction:
            waiting.append({'R' : nowTry[R].copy(), 'B' : nowTry[B].copy(), 'Depth' : nowTry[Depth] + 1, 'dirSym' : dir})
    else:
        print(1)
        exit()
print(0)