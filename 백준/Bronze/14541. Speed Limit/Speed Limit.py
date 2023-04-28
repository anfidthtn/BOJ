while True:
    n = int(input())
    if n == -1:
        break
    ans = 0
    bt = 0
    for _ in range(n):
        v, t = map(int, input().split())
        ans += v * (t - bt)
        bt = t
    print(ans, end=' miles\n')