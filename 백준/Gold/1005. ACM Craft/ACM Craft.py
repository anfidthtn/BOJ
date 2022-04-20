from collections import deque
import sys

T = int(input()) #테케 개수

for _ in range(T): #테캐만큼 반복

    N, K = map(int, sys.stdin.readline().split()) # 건물 종류, 건설 순서 개수
    BuildTime = list(map(int, sys.stdin.readline().split())) # 단일 건물 생성 시간
    PriorBuilding = [[] for _ in range(N + 1)] # 규칙 입력 1->2, 1->3, 2->3이면 [[여긴 비워두고] [2,3] [3]] 이런식
    TotalBuildTime = [0] * (N + 1) # dp돌려서 나온 건물 건설 완료 시간
    inDegree = [0] * (N + 1) # 위상 정렬할 때 체크용

    for _ in range(K):
        X, Y = map(int, sys.stdin.readline().split()) #규칙 입력받기
        PriorBuilding[X].append(Y) #규칙 저장
        inDegree[Y] += 1

    Goal = int(input()) #지어야 할 건물 번호

    q = deque() # 큐

    for i in range(1, N + 1):
        if inDegree[i] == 0: # 제한 다 없어진거(선행 계산 끝난거) 큐에 넣음
            q.append(i)

    while q:
        now = q.popleft()

        TotalBuildTime[now] += BuildTime[now - 1] # 선행계산 끝난거에 자기 건설시간 더하면 건설 완료시간 나옴
        if now == Goal:
            print(TotalBuildTime[Goal]) # 방금 계산 끝난게 목표건물이면 출력하고 종료
            break

        for i in PriorBuilding[now]:
            if TotalBuildTime[i] < TotalBuildTime[now]: # 자기 다음거에 최소 건설시작시간이 자기 시간보다 적게 되어있으면 자기 시간으로 맞춤
                TotalBuildTime[i] = TotalBuildTime[now]
            inDegree[i] -= 1 # 자기 건설시간 맞춰놨으니 자기 다음거 규칙수 하나 없앰
            if inDegree[i] == 0: # 자기 다음거 선행 계산 다 끝난거는 큐에 넣음
                q.append(i)