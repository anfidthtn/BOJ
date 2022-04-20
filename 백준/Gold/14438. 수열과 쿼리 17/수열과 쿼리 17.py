import sys

MAXNUM = 10 ** 9

N = int(sys.stdin.readline())
nums = [MAXNUM] + list(map(int, sys.stdin.readline().split())) + [MAXNUM]

segTree = [MAXNUM for _ in range(N * 4)]

def updateMinValue(nodeNum, start, end, linearIdx, value):
    if linearIdx < start or end < linearIdx:
        return segTree[nodeNum]
    
    leftMinValue = MAXNUM
    rightMinValue = MAXNUM
    
    if start != end:
        leftMinValue = updateMinValue(nodeNum * 2, start, int((start + end) / 2), linearIdx, value)
        rightMinValue = updateMinValue(nodeNum * 2 + 1, int((start + end) / 2) + 1, end, linearIdx, value)
    segTree[nodeNum] = min(leftMinValue, rightMinValue, value)
    return segTree[nodeNum]

for i in range(1, N + 1):
    updateMinValue(1, 1, N, i, nums[i])

def getMinValue(nodeNum, start, end, left, right):
    if right < start or end < left:
        return MAXNUM
    
    if left <= start and end <= right:
        return segTree[nodeNum]
    
    leftMinValue = getMinValue(nodeNum * 2, start, int((start + end) / 2), left, right)
    rightMinValue = getMinValue(nodeNum * 2 + 1, int((start + end) / 2) + 1, end, left, right)

    return min(leftMinValue, rightMinValue)

M = int(sys.stdin.readline())

for _ in range(M):
    a, b, c = map(int, sys.stdin.readline().split())
    if a == 1:
        updateMinValue(1, 1, N, b, c)
    else:
        print(getMinValue(1, 1, N, b, c))