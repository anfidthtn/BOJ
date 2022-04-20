import sys

N = int(sys.stdin.readline())

MAXIDX = 1000000

segTree = [0] * (MAXIDX * 4 + 1)

def insertCandy(nodeNum, start, end, insertIdx, amount):
    if insertIdx < start or end < insertIdx:
        return
    
    segTree[nodeNum] += amount

    if start != end:
        insertCandy(nodeNum * 2, start, int((start + end) / 2), insertIdx, amount)
        insertCandy(nodeNum * 2 + 1, int((start + end) / 2) + 1, end, insertIdx, amount)

def popCandy(nodeNum, start, end, remain):
    segTree[nodeNum] -= 1

    if start == end:
        print(start)
        return
    
    if segTree[nodeNum * 2] < remain:
        remain -= segTree[nodeNum * 2]
        popCandy(nodeNum * 2 + 1, int((start + end) / 2) + 1, end, remain)
    else:
        popCandy(nodeNum * 2, start, int((start + end) / 2), remain)

for _ in range(N):
    order = list(map(int, sys.stdin.readline().split()))
    if len(order) == 3:
        insertCandy(1, 1, MAXIDX, order[1], order[2])
    else:
        popCandy(1, 1, MAXIDX, order[1])