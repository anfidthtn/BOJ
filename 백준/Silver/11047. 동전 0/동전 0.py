n, k = map(int, input().split())
coinValues = [int(input()) for _ in range(n)]
coinValues.reverse()

sum = 0
for coin in coinValues:
    sum += k // coin
    k %= coin
print(sum)