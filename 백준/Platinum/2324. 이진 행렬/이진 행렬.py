from collections import deque

n, m  = map(int, input().split())

MAXNUM = 100 ** 2 + 1

# 상하좌우
direction = ((-1, 0), (1, 0), (0, -1), (0, 1))

# 해당 그룹번호(인덱스) 에서 연결된 다른 그룹 번호 집합의 리스트
groupInfo = []
# 그룹 넘버 매길 때 쓸거
groupNum = -1

# 최초 맵 (외부는 100 * 100보다 큰 수인 MAXNUM으로 둘러치기)
Map = []
Map.append([MAXNUM] * (m + 2))
for _ in range(n):
    Map.append([MAXNUM] + list(input().rstrip()) + [MAXNUM])
Map.append([MAXNUM] * (m + 2))

for i in range(1, n + 1):
    for j in range(1, m + 1):
        if type(Map[i][j]) == str: # 숫자가 아니면 미탐색된 곳이란 소리 (즉, 새로운 그룹의 시작점)
            groupNum += 1 # 새 그룹넘버
            groupData = Map[i][j] # 그룹의 데이터 '0' or '1'
            groupInfo.append(set()) # 인접그룹 담을 집합
            Map[i][j] = groupNum # 해당 위치를 그룹넘버로 바꿈

            waiting = deque([(i, j)]) # 해당 위치부터 bfs로 그룹화
            while len(waiting) > 0:
                now = waiting.popleft()
                for dir in direction: # 4방향 탐색
                    if type(Map[now[0] + dir[0]][now[1] + dir[1]]) == str: # 미탐색된 곳이면
                        if Map[now[0] + dir[0]][now[1] + dir[1]] == groupData: # '0' or '1'이 같은건지 확인하고 같으면
                            Map[now[0] + dir[0]][now[1] + dir[1]] = groupNum  # 해당 위치를 그룹넘버로 표시하고
                            waiting.append((now[0] + dir[0], now[1] + dir[1])) # 해당 위치를 탐색지점에 추가
                    elif Map[now[0] + dir[0]][now[1] + dir[1]] < MAXNUM: # 그룹화 된 곳이면
                        if Map[now[0] + dir[0]][now[1] + dir[1]] != groupNum: # 그룹 넘버가 같은 곳은 패스, 다른 곳은
                            groupInfo[groupNum].add(Map[now[0] + dir[0]][now[1] + dir[1]]) # 서로 연결표시
                            groupInfo[Map[now[0] + dir[0]][now[1] + dir[1]]].add(groupNum) # 서로 연결표시(2)

# 각 그룹별 bfs를 돌리기 위한 bfs를 돌림
minDepth = MAXNUM # 현재까지 파악된 최저 깊이 (early stop해야해서)
bfsPointVisited = [False] * len(groupInfo) # 탐색한 곳인지 파악할 리스트
bfsPointVisited[Map[int((n + 1) / 2)][int((m + 1) / 2)]] = True # 최초 지점은 맵의 중앙의 그룹넘버 (예외는 있겠지만 일반적으로 맵의 중앙 부근에서 깊이 밸런스가 맞을 확률이 높음)
bfsPointWaiting = deque([Map[int((n + 1) / 2)][int((m + 1) / 2)]]) # 마찬가지. 그룹 넘버를 덱에 넣음

while len(bfsPointWaiting) > 0: # 그룹 넘버에 대해 bfs로 bfs의 시작지점을 둠
    bfsIndex = bfsPointWaiting.popleft() # bfs 탐색 bfs 탐색 시작지점

    visited = [False] * len(groupInfo) # 이번 bfs에서 방문한 노드 정보 저장
    visited[bfsIndex] = True # 이번 bfs의 시작은 bfsIndex 그룹이므로 해당 그룹 True로
    waiting = deque([(bfsIndex, 0)]) # 시작번호, 최초 깊이를 넣음
    while len(waiting) > 0: # bfs 시~ 작!
        now = waiting.popleft() # 현재까지 깊이 젤 낮은거 갖고옴
        depth = now[1] # 뒤에 처리하기위해 현재까지 탐색한 깊이 저장
        if now[1] >= minDepth: # 만약에 지금까지 가장 얕았던 뎁스보다 더 깊거나 같게 나오면 끝내면 됨
            bfsPointVisited[now[0]] = True # 탐색 지점으로부터 이 위치(+ 덱에 남아있는 위치)까지 거리는 minDepth보다 크거나 같으므로 탐색한 지점이라 쳐도 됨(탐색할 필요 없기 때문)
            while len(waiting) > 0:
                bfsPointVisited[waiting.popleft()[0]] = True
            break
        for nextIdx in groupInfo[now[0]]: # minDepth보다 얕으면 더 탐색함
            if visited[nextIdx] is False:
                visited[nextIdx] = True
                waiting.append((nextIdx, now[1] + 1))
    if minDepth > depth: # depth에는 bfsIndex 그룹에서 bfs탐색을 시작했을 때 뎁스가 저장됨. 이 분기로 들어온다는거는 mindepth가 갱신된다는거임.
        minIndex = bfsIndex # 갱신할 때 탐색지점 저장해주고
        minDepth = depth # 깊이도 갱신해줌

        for nextIdx in groupInfo[bfsIndex]: # 깊이가 갱신될 때만 해당 지점에서 연결된 녀석들을 다음 탐색지점으로 지정함. 왜냐면 깊이가 갱신 안 됐으면 해당 방향으로 더 진행해도 깊이가 얕아질 수 없기 때문
            if bfsPointVisited[nextIdx] is False:
                bfsPointVisited[nextIdx] = True
                bfsPointWaiting.append(nextIdx)

print(minDepth)
isFind = False
for i in range(1, n + 1):
    for j in range(1, m + 1):
        if Map[i][j] == minIndex:
            for _ in range(minDepth):
                print(i, j, sep=' ')
            exit()