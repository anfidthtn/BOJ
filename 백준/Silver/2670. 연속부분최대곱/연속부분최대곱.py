import sys
n = int(input())
before = 1
ans = 0
for _ in range(n):
    x = float(sys.stdin.readline())
    before = max(before * x, x)
    ans = max(ans, before)
print("%.3f" % ans)