import math
a, b = map(int, input().split())
ans = 1
for i in range(1, a + 1):
    ans *= i
    ans %= b
print(ans)