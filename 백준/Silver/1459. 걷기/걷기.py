x, y, w, s = map(int, input().split())
x, y = min(x, y), max(x, y)
ans = 0
if w * 2 > s:
    ans += min(x, y) * s
    x, y = 0, y - x
    if w > s:
        ans += y // 2 * s * 2
        y %= 2
    ans += y * w
else:
    ans = (x + y) * w

print(ans)