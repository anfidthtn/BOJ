import sys

N, M, K = map(int, sys.stdin.readline().split())

MAXIDX = 1 + N
MAXNUM = 10 ** 9 + 7

segTree = [-1] * (4 * MAXIDX)

def initMul(nodeNum, start, end, updateIdx, mulValue):
    if updateIdx < start or end < updateIdx:
        return
    if segTree[nodeNum] >= 0:
        segTree[nodeNum] *= mulValue
    else:
        segTree[nodeNum] = mulValue
    segTree[nodeNum] %= MAXNUM
    if start != end:
        initMul(nodeNum * 2, start, int((start + end) / 2), updateIdx, mulValue)
        initMul(nodeNum * 2 + 1, int((start + end) / 2) + 1, end, updateIdx, mulValue)


for i in range(N):
    num = int(sys.stdin.readline())
    initMul(1, 1, MAXIDX, i + 1, num)

# start ~ end 범위(nodeNum이 갖고있는 누적곱 인덱스 범위) 안에 있는 left ~ right의 곱을 구함
def getMul(nodeNum, start, end, left, right):
    # (left, right, start, end) 순 or (start, end, left, right) 순으로 범위 벗어났으면 누적곱 1 반환
    if right < start or end < left:
        return 1
    # (left, start, end, right) 순으로 배치된 경우 start ~ end의 누적 곱 반환
    if left <= start and end <= right:
        return segTree[nodeNum]
    # left start right end / start left end right 같은 상황은
    # start와 end 범위를 재조정해서 탐색한다.
    resultL = getMul(nodeNum * 2, start, int((start + end) / 2), left, right)
    if resultL == 0:
        return 0
    resultR = getMul(nodeNum * 2 + 1, int((start + end) / 2) + 1, end, left, right)
    return (resultL * resultR) % MAXNUM

def getLeafIdx(nodeNum, start, end, linearIdx):
    if linearIdx < start or end < linearIdx:
        return
    if start == end:
        return nodeNum
    if linearIdx <= int((start + end) / 2):
        return getLeafIdx(nodeNum * 2, start, int((start + end) / 2), linearIdx)
    else:
        return getLeafIdx(nodeNum * 2 + 1, int((start + end) / 2) + 1, end, linearIdx)

def updateMul(nodeNum):
    if nodeNum < 1:
        return
    try:
        segTree[nodeNum] = (segTree[nodeNum * 2] * segTree[nodeNum * 2 + 1]) % MAXNUM
    except:
        segTree[nodeNum] = segTree[nodeNum * 2]
    updateMul(int(nodeNum / 2))

for _ in range(M + K):
    a, b, c = map(int, sys.stdin.readline().split())
    if a == 1: # 값 수정 때
        leafIdx = getLeafIdx(1, 1, MAXIDX, b)
        segTree[leafIdx] = c
        updateMul(int(leafIdx / 2))
    else:
        print(getMul(1, 1, MAXIDX, b, c))