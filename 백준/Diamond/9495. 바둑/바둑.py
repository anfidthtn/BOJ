import sys
sys.setrecursionlimit(10**6)

n = int(sys.stdin.readline())

# 방향
direction = ((-1, 0), (1, 0), (0, -1), (0, 1))

Map = []
for i in range(0, n + 2):
    tempMap = []
    if i == 0 or i == n + 1:
        tempMap = ['x' for _ in range(n + 2)]
    else:
        tempMap.append('x')
        tempMap.extend(list(sys.stdin.readline()))
        tempMap.append('x')
    Map.append(tempMap)

# 흰 돌이 주변 어떤 빈 칸과 연결되어있는지 상태를 저장하는 딕셔너리. 키는 흰 돌의 좌표, 값은 연결된 모든 빈 공간의 좌표
edgeInfo = {}
# 흰 돌 주변 빈 칸들이 어떤 흰 돌이랑 연결된 상태인지 저장하는 딕셔너리. 키는 빈 칸의 좌표를 튜플화 한 것, 값은 탐색 중 (임시)저장된 흰 돌의 좌표
matchInfo = {}
# 이번 탐색에서 어느 지점들을 체크했는지 확인
isChecked = {}
# 최초 빈 칸 개수
spaceCount = 0

for i in range(1, n + 1):
    for j in range(1, n + 1):
        if Map[i][j] == 'o':
            # 흰 돌이 나오면 키 값을 추가함
            edgeInfo[(i, j)] = []
            for dir in direction: # 4방향에 대해서 빈 칸('.')이 있는지 확인
                newRow = i + dir[0]
                newCol = j + dir[1]
                if Map[newRow][newCol] == '.': # 빈 칸이 있으면
                    edgeInfo[(i, j)].append((newRow, newCol)) # 흰 돌과의 연결관계 추가
                    matchInfo[(newRow, newCol)] = None # 빈 칸 키값 추가 (중복되지 않는 키값 특성으로 개수 세는 역할도 함)
                    isChecked[(newRow, newCol)] = False # 빈 칸 키값 추가 (위와 동일)
        # 빈 칸이면 빈 칸 개수 올림
        if Map[i][j] == '.':
            spaceCount += 1

def clearChecked(): # 한 지점에서 탐색 시작할 때마다 탐색지점 초기화
    for key in isChecked.keys():
        isChecked[key] = False


def isMatchable(whitePoint): # dfs형식으로 매칭이 되는 흰 돌인지 파악
    for adjSpace in edgeInfo[whitePoint]: # 흰 돌과 연결된 빈 공간 좌표에 대해서
        if isChecked[adjSpace] is True: # 해당 좌표가 이미 이번 탐색에서 탐색된 곳이면 패스
            continue
        isChecked[adjSpace] = True # 탐색되지 않은 곳이면 탐색한 곳으로 등록
        # 탐색되지 않은 곳인데 매칭도 되지 않은 곳이라면 해당 지점을 매칭
        # 매칭이 된 곳이라면 이미 매칭이 되어있는 흰 돌을 다른 빈 공간과 매칭할 수 있을지 재귀적으로 탐색
        if matchInfo[adjSpace] is None or isMatchable(matchInfo[adjSpace]) is True:
            matchInfo[adjSpace] = whitePoint # 이 빈공간에 흰 돌을 매칭할 수 있으면 매칭
            return True # 매칭할 수 있었으므로 True 반환
    return False # 매칭할 수 없었으므로 False 반환

maxMatchCount = 0 # 최대 매칭 카운트

for whitePoint in edgeInfo.keys():
    clearChecked() # 탐색지점 정보 초기화
    if isMatchable(whitePoint) is True: # 흰 돌이 (앞서 매칭한 돌의 매칭상태까지 생각해서) 매칭 가능한 돌이면
        maxMatchCount += 1 # 매칭 가능하니까 +1해줌

print(spaceCount + len(edgeInfo) - maxMatchCount) # 최대 빈 칸 개수 출력