from collections import deque

X = int(input())
leaving = deque([64])
leavingSum = 64

while leavingSum > X:
    shortest = leaving.popleft()
    div2 = int(shortest / 2)
    if leavingSum - div2 < X:
        leaving.appendleft(div2)
        leaving.appendleft(div2)
    else:
        leavingSum -= div2
        leaving.appendleft(div2)

print(len(leaving))