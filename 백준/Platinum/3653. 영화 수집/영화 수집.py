import sys

T = int(sys.stdin.readline())

def setSum(segTree, nodeNum, start, end, changeIdx, value):
    if changeIdx < start or end < changeIdx:
        return
    
    segTree[nodeNum] += value

    if start != end:
        setSum(segTree, nodeNum * 2, start, (start + end) >> 1, changeIdx, value)
        setSum(segTree, nodeNum * 2 + 1, ((start + end) >> 1) + 1, end, changeIdx, value)

def getSum(segTree, nodeNum, start, end, left, right):
    if right < start or end < left:
        return 0
    
    if left <= start and end <= right:
        return segTree[nodeNum]
    
    return getSum(segTree, nodeNum * 2, start, (start + end) >> 1, left, right) + getSum(segTree, nodeNum * 2 + 1, ((start + end) >> 1) + 1, end, left, right)

for _ in range(T):
    n, m = map(int, sys.stdin.readline().split())
    segTree = [0] * (4 * (n + m + 1))
    loc = [None] + [i for i in range(n, 0, -1)]
    for i in range(1, n + 1):
        setSum(segTree, 1, 1, n + m, i, 1)
    order = list(map(int, sys.stdin.readline().split()))
    for i in range(m):
        print(getSum(segTree, 1, 1, n + m, loc[order[i]] + 1, n + i), end=' ')
        setSum(segTree, 1, 1, n + m, loc[order[i]], -1)
        setSum(segTree, 1, 1, n + m, n + i + 1, 1)
        loc[order[i]] = n + i + 1
    print()
