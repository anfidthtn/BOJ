a, b = map(int, input().split())
if a == b:
    print(0)
    pass
else:
    a, b = min(a, b), max(a, b)
    print(b - a - 1)
    for i in range(a + 1, b):
        print(i, end=' ')