a, b = map(int, input().split())
a, b = min(a, b), max(a, b)
aa = -1 if a < 0 else 1
bb = -1 if b < 0 else 1
a *= aa
b *= bb
asum = a * (a + 1) // 2
bsum = b * (b + 1) // 2
if aa >= 0:
    print(bsum - asum + a)
elif bb >= 0:
    print(bsum - asum)
else:
    print(-asum + bsum - b)