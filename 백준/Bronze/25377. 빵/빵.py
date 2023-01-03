n = int(input())
res = 1 << 20
for _ in range(n):
    a, b = map(int, input().split())
    if a <= b:
        res = min(res, b)
if res != 1 << 20:
    print(res)
else:
    print(-1)