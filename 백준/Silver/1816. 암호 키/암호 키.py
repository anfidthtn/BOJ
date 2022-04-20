isPrime = [True] * (10 ** 6 + 1)
prime = []
for primeIdx in range(2, 10 ** 6 + 1):
    if primeIdx < 2:
        isPrime[primeIdx] = False
    if isPrime[primeIdx] is True:
        prime.append(primeIdx)
        for idx in range(2 * primeIdx, 10 ** 6 + 1, primeIdx):
            isPrime[idx] = False

N = int(input())
for _ in range(N):
    num = int(input())
    isValid = True
    for p in prime:
        if num % p == 0:
            print('NO')
            isValid = False
            break
    if isValid is True:
        print('YES')
        