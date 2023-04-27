import math
n, k = map(int, input().split())
n -= 1
k -= 1

print(math.factorial(n) // math.factorial(k) // math.factorial(n - k))