import sys

T = int(sys.stdin.readline())
for _ in range(T):
    result = [0] * 100
    a, b = sys.stdin.readline().split()
    a = list(a)
    b = list(b)
    a.reverse()
    b.reverse()
    for i in range(len(a)):
        result[i] += int(a[i])
    for i in range(len(b)):
        result[i] += int(b[i])
    for i in range(max(len(a), len(b))):
        if result[i] >= 2:
            result[i] &= 1
            result[i + 1] += 1
    result.reverse()
    try:
        for i in range(result.index(1), 100):
            print(result[i], end='')
        print()
    except:
        print(0)