import sys
N = int(sys.stdin.readline())

MAXNUM = 10 ** 9 + 7
MAXIDX = 200001

# 0 ~ 200000까지 [구간합, 구간개수]저장
# 근데 미리 만들어두면 공간낭비 날 것 같아서 딕셔너리로 저장
treeL = {}

# start ~ end 범위(nodeNum이 갖고있는 누적합 인덱스 범위) 안에 있는 left ~ right의 합을 구함
def getSum(nodeNum, start, end, left, right):
    # (left, right, start, end) 순 or (start, end, left, right) 순으로 범위 벗어났으면 누적합 0, 누적개수 0 반환
    if right < start or end < left:
        return [0, 0]
    # (left, start, end, right) 순으로 배치된 경우 start ~ end의 누적 합, 누적 개수 반환
    # 이 때 left == start or end == right 둘 중 하나는 만족함
    if left <= start and end <= right:
        try:
            # 값이 있으면 반환
            return treeL[nodeNum]
        except:
            # 없으면 0, 0 반환
            treeL[nodeNum] = [0, 0]
            return treeL[nodeNum]
    # left start right end / start left end right 같은 상황은
    # start와 end 범위를 재조정해서 탐색한다.
    resultL = getSum(nodeNum * 2, start, int((start + end) / 2), left, right)
    resultR = getSum(nodeNum * 2 + 1, int((start + end) / 2) + 1, end, left, right)
    return [resultL[0] + resultR[0], resultL[1] + resultR[1]]

def updateSum(nodeNum, start, end, updateIdx, plusValue):
    if updateIdx < start or end < updateIdx:
        return
    try:
        treeL[nodeNum][0] += plusValue
        treeL[nodeNum][1] += 1
    except:
        treeL[nodeNum] = [plusValue, 1]
    if start != end:
        updateSum(nodeNum * 2, start, int((start + end) / 2), updateIdx, plusValue)
        updateSum(nodeNum * 2 + 1, int((start + end) / 2) + 1, end, updateIdx, plusValue)

# 첫 위치는 심기만 하기
inputNum = int(sys.stdin.readline())
updateSum(1, 1, MAXIDX, inputNum + 1, inputNum)

mul = 1
for _ in range(N - 1):
    inputNum = int(sys.stdin.readline())
    leftSum = getSum(1, 1, MAXIDX, 1, inputNum)
    rightSum = getSum(1, 1, MAXIDX, inputNum + 2, MAXIDX)
    cost = 0
    cost += inputNum * leftSum[1] - leftSum[0]
    cost += rightSum[0] - inputNum * rightSum[1]
    cost %= MAXNUM
    if cost == 0:
        print(0)
        exit()
    mul = (mul * cost) % MAXNUM
    updateSum(1, 1, MAXIDX, inputNum + 1, inputNum)
print(mul)