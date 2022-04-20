import sys

N, M, K = map(int, sys.stdin.readline().split())

MAXIDX = 1 + N

segTree = {}

def updateSum(nodeNum, start, end, updateIdx, plusValue):
    if updateIdx < start or end < updateIdx:
        return
    try:
        segTree[nodeNum] += plusValue
    except:
        segTree[nodeNum] = plusValue
    if start != end:
        updateSum(nodeNum * 2, start, int((start + end) / 2), updateIdx, plusValue)
        updateSum(nodeNum * 2 + 1, int((start + end) / 2) + 1, end, updateIdx, plusValue)

for i in range(N):
    num = int(sys.stdin.readline())
    updateSum(1, 1, MAXIDX, i + 1, num)

# start ~ end 범위(nodeNum이 갖고있는 누적합 인덱스 범위) 안에 있는 left ~ right의 합을 구함
def getSum(nodeNum, start, end, left, right):
    # (left, right, start, end) 순 or (start, end, left, right) 순으로 범위 벗어났으면 누적합 0 반환
    if right < start or end < left:
        return 0
    # (left, start, end, right) 순으로 배치된 경우 start ~ end의 누적 합 반환
    # 이 때 left == start or end == right 둘 중 하나는 만족함
    if left <= start and end <= right:
        try:
            # 값이 있으면 반환
            return segTree[nodeNum]
        except:
            # 없으면 0 반환
            segTree[nodeNum] = 0
            return segTree[nodeNum]
    # left start right end / start left end right 같은 상황은
    # start와 end 범위를 재조정해서 탐색한다.
    resultL = getSum(nodeNum * 2, start, int((start + end) / 2), left, right)
    resultR = getSum(nodeNum * 2 + 1, int((start + end) / 2) + 1, end, left, right)
    return resultL + resultR

for _ in range(M + K):
    a, b, c = map(int, sys.stdin.readline().split())
    if a == 1:
        updateSum(1, 1, MAXIDX, b, c - getSum(1, 1, MAXIDX, b, b))
    else:
        print(getSum(1, 1, MAXIDX, b, c))