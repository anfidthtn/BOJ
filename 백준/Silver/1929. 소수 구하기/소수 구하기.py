m, n = map(int, input().split())

isPrime = [False] * 2 + [True] * (n - 1)

for i in range(2, n + 1):
    if isPrime[i] is False:
        continue
    for j in range(i * 2, i * (n + 1), i):
        if j > n:
            break
        isPrime[j] = False

for i in range(m, n + 1):
    if isPrime[i] is True:
        print(i)