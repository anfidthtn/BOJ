import sys

N, M = map(int, sys.stdin.readline().split())

MINNUM = 0
MAXNUM = 10 ** 9 + 1
segTree = [[MAXNUM, MINNUM] for _ in range(N * 4 + 1)]

def insertNum(nodeNum, start, end, insertIdx, number):
    if insertIdx < start or end < insertIdx:
        return
    
    if segTree[nodeNum][0] > number:
        segTree[nodeNum][0] = number
    if segTree[nodeNum][1] < number:
        segTree[nodeNum][1] = number
    
    if start != end:
        insertNum(nodeNum * 2, start, int((start + end) / 2), insertIdx, number)
        insertNum(nodeNum * 2 + 1, int((start + end) / 2) + 1, end, insertIdx, number)

def getMinMax(nodeNum, start, end, left, right):
    if right < start or end < left:
        return [MAXNUM, MINNUM]
    
    if left <= start and end <= right:
        return segTree[nodeNum]
    
    leftMinMax = getMinMax(nodeNum * 2, start, int((start + end) / 2), left, right)
    rightMinMax = getMinMax(nodeNum * 2 + 1, int((start + end) / 2) + 1, end, left, right)
    result = [MAXNUM, MINNUM]
    result[0] = min(leftMinMax[0], rightMinMax[0])
    result[1] = max(leftMinMax[1], rightMinMax[1])
    return result

for i in range(1, N + 1):
    insertNum(1, 1, N, i, int(sys.stdin.readline()))

for _ in range(M):
    a, b = map(int, sys.stdin.readline().split())
    result = getMinMax(1, 1, N, a, b)
    print(result[0], result[1], sep=' ')