import sys

n, k = map(int, sys.stdin.readline().split())
coins = set([int(sys.stdin.readline()) for _ in range(n)])
coins = list(coins)
coins.sort()

MAXIMUM = 10 ** 10

minCoin = [0] + [MAXIMUM] * k

for i in range(1, k + 1):
    for coin in coins:
        if i - coin >= 0:
            minCoin[i] = min(minCoin[i], minCoin[i - coin] + 1)

if minCoin[k] == MAXIMUM:
    print(-1)
else:
    print(minCoin[k])