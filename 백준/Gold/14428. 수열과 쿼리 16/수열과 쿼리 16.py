import sys

MAXNUM = 10 ** 9

N = int(sys.stdin.readline())
nums = [MAXNUM] + list(map(int, sys.stdin.readline().split())) + [MAXNUM]

segTree = [N + 1 for _ in range(N * 4)]

def initMinIdx(nodeNum, start, end, linearIdx):
    if linearIdx < start or end < linearIdx:
        return
    
    if nums[linearIdx] == nums[segTree[nodeNum]]:
        segTree[nodeNum] = min(segTree[nodeNum], linearIdx)
    elif nums[linearIdx] < nums[segTree[nodeNum]]:
        segTree[nodeNum] = linearIdx
    
    if start != end:
        initMinIdx(nodeNum * 2, start, int((start + end) / 2), linearIdx)
        initMinIdx(nodeNum * 2 + 1, int((start + end) / 2) + 1, end, linearIdx)

for i in range(1, N + 1):
    initMinIdx(1, 1, N, i)

def getMinIdx(nodeNum, start, end, left, right):
    if right < start or end < left:
        return N + 1
    
    if left <= start and end <= right:
        return segTree[nodeNum]
    
    leftMinIdx = getMinIdx(nodeNum * 2, start, int((start + end) / 2), left, right)
    rightMinIdx = getMinIdx(nodeNum * 2 + 1, int((start + end) / 2) + 1, end, left, right)

    if nums[leftMinIdx] == nums[rightMinIdx]:
        if leftMinIdx > rightMinIdx:
            return rightMinIdx
        else:
            return leftMinIdx
    elif nums[leftMinIdx] < nums[rightMinIdx]:
        return leftMinIdx
    else:
        return rightMinIdx

def getLeafIdx(nodeNum, start, end, linearIdx):
    if linearIdx < start or end < linearIdx:
        return
    if start == end:
        return nodeNum
    if linearIdx <= int((start + end) / 2):
        return getLeafIdx(nodeNum * 2, start, int((start + end) / 2), linearIdx)
    else:
        return getLeafIdx(nodeNum * 2 + 1, int((start + end) / 2) + 1, end, linearIdx)

def updateMinIdx(nodeNum):
    nodeNum = int(nodeNum / 2)
    if nodeNum < 1:
        return

    if nums[segTree[nodeNum * 2]] == nums[segTree[nodeNum * 2 + 1]]:
        segTree[nodeNum] = min(segTree[nodeNum * 2], segTree[nodeNum * 2] + 1)
    elif nums[segTree[nodeNum * 2]] < nums[segTree[nodeNum * 2 + 1]]:
        segTree[nodeNum] = segTree[nodeNum * 2]
    else:
        segTree[nodeNum] = segTree[nodeNum * 2 + 1]

    updateMinIdx(nodeNum)

M = int(sys.stdin.readline())

for _ in range(M):
    a, b, c = map(int, sys.stdin.readline().split())
    if a == 1:
        nums[b] = c
        updateMinIdx(getLeafIdx(1, 1, N, b))
    else:
        print(getMinIdx(1, 1, N, b, c))