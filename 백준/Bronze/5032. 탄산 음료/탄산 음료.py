e, f, c = map(int,input().split())
ans = 0
e += f
while e >= c:
    ans += 1
    e -= c
    e += 1
print(ans)