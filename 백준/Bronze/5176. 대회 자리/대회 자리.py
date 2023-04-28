import sys
input = sys.stdin.readline
n = int(input())
for _ in range(n):
    p, m = map(int, input().split())
    check = [False for _ in range(m + 1)]
    ans = 0
    for _ in range(p):
        x = int(input())
        if check[x]:
            ans += 1
        check[x] = True
    print(ans)