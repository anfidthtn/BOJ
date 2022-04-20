import sys
sys.setrecursionlimit(10 ** 6)

test = False

n, m = map(int, sys.stdin.readline().split())
zido = [[' '] * (m + 4)] + [[' '] + ['.'] * (m + 2) + [' ']] + [[' '] + ['.'] + list(sys.stdin.readline().rstrip()) + ['.'] + [' '] for _ in range(n)] + [[' '] + ['.'] * (m + 2) + [' ']] + [[' '] * (m + 4)]
'''
zido : 전체 지도
원래 지도의 바깥을 바다로 한 번 감싸고
그 감싼 것의 바깥을 빈 칸으로 감쌌다.
그 이유는 가장 바깥과 연결된 바다의 높이를 0으로 만드는 과정에서
원래 지도 내부에 0인 바다가 없는 경우가 있을 수 있는데, 이러면 예외처리를 따로 하기 힘들어지기 때문에
바깥에 바다로 한 번 감싸서 0인 바다가 무조건 나오도록 하여 그 지점을 시점으로 높이의 계산을 시작하도록 하기 위함이다.
가장 바깥 빈칸은 인덱스 문제때문에 감쌌다.
'''
# 섬의 그룹 넘버정보(그룹화 때 방문정보 체크용이기도 함)
islandGroupNums = [[-1 for _ in range(m + 4)] for _ in range(n + 4)]

# 테스트용 출력함수
def testPrint(LIST):
    for line in LIST:
        for num in line:
            if str(num) == "-1":
                print("   ", end='')
            else:
                print("%3s" % str(num), end='')
        print()
    print()

if test is True:
    testPrint(zido)

# 8방향의 섬
islandDir = ((-1, -1), (1, -1), (0, -1), (-1, 0), (1, 0), (-1, 1), (0, 1), (1, 1))
# 4방향의 바다
seaDir = ((-1, 0), (1, 0), (0, -1), (0, 1))


# 이번 그룹의 숫자
nowGroupNum = 0

# 순회하며 그룹화가 안 된 섬을 그룹화하고, 그룹 개수를 더해준다.
for row in range(2, n + 2):
    for col in range(2, m + 2):
        if zido[row][col] == 'x' and islandGroupNums[row][col] == -1:
            # 그룹 지점 스택에 해당 지점을 넣고 탐색 시작
            groupStack = [(row, col)]
            # 해당 지점을 그룹화된 지점으로 전환
            nowGroupNum += 1
            islandGroupNums[row][col] = nowGroupNum
            while len(groupStack) > 0:
                nowRow, nowCol = groupStack.pop()
                for iDir in islandDir:
                    nextRow = nowRow + iDir[0]
                    nextCol = nowCol + iDir[1]
                    if zido[nextRow][nextCol] == 'x' and islandGroupNums[nextRow][nextCol] == -1:
                        # 그룹 넘버를 등록
                        islandGroupNums[nextRow][nextCol] = nowGroupNum
                        # 다음 탐색지점으로 등록
                        groupStack.append((nextRow, nextCol))

'''
섬이 없다면 -1을 출력하고 끝낸다.
'''
if nowGroupNum == 0:
    print(-1)
    exit()

if test is True:
    testPrint(islandGroupNums)

# 섬과 바다의 방문여부
isMapVisited = [[-1 for _ in range(m + 4)] for _ in range(n + 4)]
isGroupVisited = [False for _ in range(nowGroupNum + 1)]

# 섬들간의 연관관계 루트가 0인 트리로 만듦
childs = [[] for _ in range(nowGroupNum + 1)]

'''
가장 바깥쪽 바다부터 탐색하여, 연결된 바다끼리는 같은 숫자, 그룹 내부로 고립된 바다는 그룹의 숫자로 전파
해당 전파가 계속되며 섬이 발견되면 해당 섬의 그룹넘버는 해당 바다의 그룹넘버의 자식으로 등록
'''
isMapVisited[1][1] = 0
isGroupVisited[0] = True
seaStack = [(1, 1, 0)]
islandStack = []

while len(seaStack) > 0 or len(islandStack) > 0:
    while len(seaStack) > 0:
        '''
        여기에서는 바다를 같은 숫자로 전파한다.
        '''
        row, col, num = seaStack.pop()
        for sDir in seaDir:
            '''
            바다가 전파될 수 있는 4방향에 대해서 탐색
            '''
            nextRow = row + sDir[0]
            nextCol = col + sDir[1]
            if zido[nextRow][nextCol] == '.' and isMapVisited[nextRow][nextCol] == -1:
                '''
                연결된 바다가 방문하지 않은 바다 지점이면
                같은 넘버로 넘버링하고 다음 탐색 지점으로도 등록한다.
                '''
                isMapVisited[nextRow][nextCol] = num
                seaStack.append((nextRow, nextCol, num))
            if zido[nextRow][nextCol] == 'x':
                nextGroupNum = islandGroupNums[nextRow][nextCol]
                if isGroupVisited[nextGroupNum] is False:
                    '''
                    탐색 지점이 섬이고, 해당 섬의 그룹 번호(islandGroupNums[nextRow][nextCol])가 방문하지 않은 그룹이라면
                    해당 그룹 넘버를 방문한 그룹으로 등록하고
                    해역 넘버(이전 그룹의 넘버)의 자식으로 새로 발견된 그룹의 넘버를 추가하고
                    해당 위치를 다음 섬 탐색 지점으로 등록한다. (다음 섬 탐색지점에서는 어차피 그룹 넘버가 있어서 그룹 넘버는 넣을 필요가 없다.)
                    '''
                    isGroupVisited[nextGroupNum] = True
                    childs[num].append(nextGroupNum)
                    isMapVisited[nextRow][nextCol] = nextGroupNum
                    islandStack.append((nextRow, nextCol))

    while len(islandStack) > 0:
        '''
        여기에서는 섬의 그룹을 순회하며 섬의 그룹넘버로 주변 바다에 전파한다.
        '''
        row, col = islandStack.pop()
        for iDir in islandDir:
            '''
            섬이 전파될 수 있는 8방향에 대해 탐색
            '''
            nextRow = row + iDir[0]
            nextCol = col + iDir[1]
            if zido[nextRow][nextCol] == 'x' and isMapVisited[nextRow][nextCol] == -1:
                '''
                연결된 방문되지 않은 섬이면 섬의 번호로 방문했다고 등록하고 해당 지점을 탐색지점에 추가
                '''
                nextGroupNum = islandGroupNums[nextRow][nextCol]
                isMapVisited[nextRow][nextCol] = nextGroupNum
                islandStack.append((nextRow, nextCol))
            if zido[nextRow][nextCol] == '.' and isMapVisited[nextRow][nextCol] == -1:
                '''
                연결된 바다이면서 방문되지 않은 바다라면 해당 위치의 바다를 섬의 그룹 번호로 등록
                '''
                nextGroupNum = islandGroupNums[row][col]
                isMapVisited[nextRow][nextCol] = nextGroupNum
                seaStack.append((nextRow, nextCol, nextGroupNum))

if test is True:
    testPrint(isMapVisited)
    print(childs)


levels = [-1] * (nowGroupNum + 1)
'''
levels : 각 섬 그룹의 높이를 저장하기 위한 리스트
'''

'''
getLevel(groupNum) : 섬 그룹 하나의 높이를 구하기 위한 재귀함수
해당 섬이 child를 하나도 갖지 않으면 내부에 속한 섬이 없기에 높이가 0이다.
이미 level을 구한 곳이라면 구해둔 level을 반환한다.
child도 있고, level도 구하지 않은 섬의 그룹이라면 모든 자식의 level을 조사한 후, 최대치 + 1을
현재 섬 그룹의 높이로 만들고 저장, 반환한다.
'''
def getLevel(groupNum):
    if len(childs[groupNum]) == 0:
        levels[groupNum] = 0
        return 0
    if levels[groupNum] >= 0:
        return levels[groupNum]
    childLevels = []
    for child in childs[groupNum]:
        childLevels.append(getLevel(child))
    levels[groupNum] = max(childLevels) + 1
    return levels[groupNum]

'''
각 그룹별로 재귀적으로 level을 구한다.
'''
for i in range(1, len(levels)):
    levels[i] = getLevel(i)

if test is True:
    print(levels)

for i in range(max(levels) + 1):
    '''
    0부터 최고높이까지 개수를 세어서 출력한다.
    '''
    print(levels.count(i), end=' ')