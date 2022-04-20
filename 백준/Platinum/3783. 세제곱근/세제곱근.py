import sys

t = int(sys.stdin.readline())

for _ in range(t):
    n = int(sys.stdin.readline()) * (10 ** 30)
    target = 1
    digit = 0
    while (target * 10) ** 3 < n:
        target *= 10
        digit += 1
    for expo in range(digit, -1, -1):
        while (target + 10 ** expo) ** 3 <= n:
            target += 10 ** expo
    print(str(target)[:-10], '.', str(target)[-10:], sep='')
