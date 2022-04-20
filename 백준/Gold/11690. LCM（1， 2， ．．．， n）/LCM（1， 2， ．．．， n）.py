import sys
n = int(input())
if n == 1:
    print(1)
    exit()
result = 1
isPrime = [(1 << 32) - 1] * ((n >> 6) + 1)
temp = 2
while temp * 2 < n:
    temp *= 2
result *= temp
result &= ((1 << 32) - 1)

for i in range(3, n + 1, 2):
    if isPrime[i >> 6] & (1 << ((i >> 1) & 31)) == 0:
        continue
    for j in range(3 * i, n + 1, i << 1):
        isPrime[j >> 6] &= ((1 << 32) - 1) ^ (1 << ((j >> 1) & 31))
    temp = i
    while temp * i < n:
        temp *= i
    result *= temp
    result &= ((1 << 32) - 1)
print(result)