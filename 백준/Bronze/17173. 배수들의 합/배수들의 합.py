n, m = map(int, input().split())
check = [False for _ in range(n + 1)]
for num in map(int, input().split()):
    for i in range(num, n + 1, num):
        check[i] = True

ans = 0
for i in range(1, n + 1):
    if check[i]:
        ans += i
print(ans)