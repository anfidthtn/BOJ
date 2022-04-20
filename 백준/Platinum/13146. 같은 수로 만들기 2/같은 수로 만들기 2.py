import sys
MAXNUM = 10 ** 20
n = int(sys.stdin.readline())
waitingList = [MAXNUM] + [0] * n
top = 0
count = 0
for _ in range(n):
    a = int(sys.stdin.readline())
    if waitingList[top] < a:
        count += a - waitingList[top]
        while waitingList[top] < a:
            top -= 1
    if waitingList[top] > a:
        top += 1
        waitingList[top] = a
if top > 1:
    count += waitingList[1] - waitingList[top]

print(count)