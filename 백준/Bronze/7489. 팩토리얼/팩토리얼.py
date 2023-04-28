import math
n = int(input())
for _ in range(n):
    x = int(input())
    k = math.factorial(x)
    k //= 10 ** (x // 5 + x // 25 + x // 125 + x // 625)
    k %= 10
    print(k)