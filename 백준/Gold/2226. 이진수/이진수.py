n = int(input())

zeroGroup = [None, 0, 1, 1, 3]

for i in range(5, n + 1):
    if i % 2 == 0:
        next = zeroGroup[-1]
        next += zeroGroup[-2] * 2
    else:
        next = zeroGroup[-1] * 2 - 1
    zeroGroup.append(next)

print(zeroGroup[n])