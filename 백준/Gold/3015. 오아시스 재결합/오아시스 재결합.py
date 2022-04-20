import sys

N = int(sys.stdin.readline())

line = [2 ** 32] + [-1] * (N + 1)

top = 0

def getMinIdx(left, right, num):
    if left == right:
        return left
    if line[(left + right) >> 1] > num:
        return getMinIdx(((left + right) >> 1) + 1, right, num)
    else:
        return getMinIdx(left, (left + right) >> 1, num)

count = 0

for _ in range(N):
    latest = int(sys.stdin.readline())
    if top > 0:
        line[top + 1] = -1
        newTop = getMinIdx(1, top + 1, latest - 1)
        count += top - newTop + 1
        top = newTop
    else:
        top += 1
    line[top] = latest
    newTop = getMinIdx(1, top, latest)
    count += top - newTop
    if newTop >= 2:
        count += 1

print(count)