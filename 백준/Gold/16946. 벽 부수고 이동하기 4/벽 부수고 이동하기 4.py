from collections import deque

N, M = map(int, input().split())

Map = []
Map.append(['#'] * (M + 2))
for _ in range(N):
    temp = ['#']
    temp.extend(list(input().replace('1', '#')))
    temp.append('#')
    Map.append(temp)
Map.append(['#'] * (M + 2))

direction = ((-1, 0), (1, 0), (0, -1), (0, 1))

groupNum = 0
groupSize = []

def initGroup(row, col, num):
    if Map[row][col] != '0':
        return
    Map[row][col] = num
    groupSize[num] += 1

    for dir in direction:
        tempDeque.append((row + dir[0], col + dir[1]))

tempDeque = deque()
for row in range(1, N + 1):
    for col in range(1, M + 1):
        if Map[row][col] == '0':
            groupSize.append(0)
            tempDeque.append((row, col))
            while len(tempDeque) > 0:
                cord = tempDeque.popleft()
                initGroup(cord[0], cord[1], groupNum)
            groupNum += 1

resultMap = [[0] * (M + 2) for _ in range(N + 2)]


def getNum(row, col):
    count = 0
    groupNumSet = set()
    for dir in direction:
        if type(Map[row + dir[0]][col + dir[1]]) == int:
            groupNumSet.add(Map[row + dir[0]][col + dir[1]])

    for gNum in groupNumSet:
        count += groupSize[gNum]
    return count

for row in range(1, N + 1):
    for col in range(1, M + 1):
        if Map[row][col] == '#':
            resultMap[row][col] = (1 + getNum(row, col)) % 10

for row in range(1, N + 1):
    for col in range(1, M + 1):
        print(resultMap[row][col], end='')
    print()