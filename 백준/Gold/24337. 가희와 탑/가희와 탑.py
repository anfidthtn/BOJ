import sys
n, a, b = map(int, sys.stdin.readline().split())
if a + b - 1 > n:
    print(-1)
    exit()
if a > 1:
    result = [i for i in range(1, a + 1)]
    if result[-1] < b:
        result[-1] = b

    for i in range(b - 1, 0, -1):
        result.append(i)
    for i in range(n - len(result)):
        print(1, end=' ')
    for num in result:
        print(num, end=' ')
else:
    print(b, end=' ')
    for i in range(n - b):
        print(1, end=' ')
    for i in range(b - 1, 0, -1):
        print(i, end=' ')