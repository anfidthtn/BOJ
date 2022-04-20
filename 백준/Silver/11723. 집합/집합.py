import sys

m = int(input())

s = 0

for _ in range(m):
    order = list(map(str, sys.stdin.readline().split()))
    if order[0] == 'add':
        s |= 1 << (int(order[1]) - 1)
    elif order[0] == 'remove':
        s &= ~(1 << (int(order[1]) - 1))
    elif order[0] == 'check':
        if s & (1 << (int(order[1]) - 1)) > 0:
            print(1)
        else:
            print(0)
    elif order[0] == 'toggle':
        if s & (1 << (int(order[1]) - 1)) > 0:
            s &= ~(1 << (int(order[1]) - 1))
        else:
            s |= 1 << (int(order[1]) - 1)
    elif order[0] == 'all':
        s = (1 << 20) - 1
    elif order[0] == 'empty':
        s = 0