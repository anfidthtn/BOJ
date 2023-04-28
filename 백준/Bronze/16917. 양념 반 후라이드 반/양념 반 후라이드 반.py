a, b, c, x, y = map(int,input().split())
ans = 0
nx = 0
ny = 0
x *= 2
y *= 2
while a + b >= 2 * c and nx < x and ny < y:
    nx += 1
    ny += 1
    ans += c
while nx < x - 1:
    nx += 2
    ans += min(a, 2 * c)
if nx == x - 1:
    nx += 1
    ans += min(a, c)
while ny < y - 1:
    ny += 2
    ans += min(b, 2 * c)
if ny == y - 1:
    ny += 1
    ans += min(b, c)

print(ans)