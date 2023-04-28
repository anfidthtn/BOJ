import math
n = int(input())
nums = list(map(int, input().split()))
a = 1
b = 1
for i in range(1, n):
    a *= nums[i - 1]
    b *= nums[i]
    a, b = a // math.gcd(a, b), b // math.gcd(a, b)
    print(a, b, sep='/')