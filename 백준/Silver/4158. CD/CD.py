import sys
input = sys.stdin.readline
while True:
    n,m = map(int,input().split())
    if n + m == 0:
        break
    a = set()
    for _ in range(n):
        a.add(int(input()))

    ans = 0
    for _ in range(m):
        if int(input()) in a:
            ans += 1

    print(ans)