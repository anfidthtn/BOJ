a, k = map(int,input().split())
ans = 0
while a < k:
    if k & 1 == 1:
        k -= 1
        ans += 1
    elif a * 2 <= k:
        k >>= 1
        ans += 1
    else:
        k -= 1
        ans += 1
print(ans)