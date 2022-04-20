min, max = map(int, input().split())
isSquareNONO = [True] * (max - min + 1)
isPrime = [True] * (int(max ** 0.5) + 2)
for primeIdx in range(2, int(max ** 0.5) + 2):
    if primeIdx < 2:
        isPrime[primeIdx] = False
    if isPrime[primeIdx] is True:
        for idx in range(2 * primeIdx, int(max ** 0.5) + 2, primeIdx):
            isPrime[idx] = False
        for idx in range((-min) % (primeIdx ** 2), max - min + 1, primeIdx ** 2):
            isSquareNONO[idx] = False

print(isSquareNONO.count(True))