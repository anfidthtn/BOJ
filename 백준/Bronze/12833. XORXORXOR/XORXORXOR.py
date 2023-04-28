a, b, c = map(int, input().split())
if c & 1:
    print(a ^ b)
else:
    print(a)