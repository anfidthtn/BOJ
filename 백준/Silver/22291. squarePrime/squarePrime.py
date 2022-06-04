def isSquare(n):
    if n < 0:
        return False
    sqrtN = int(n ** 0.5)
    if sqrtN ** 2 == n:
        return True
    return False


def P2(A):
    n = len(A)
    # ADD ADDITIONAL CODE HERE!
    primeList = [True for _ in range(n)]
    for i in range(2, n):
        if primeList[i]:
            for j in range(2 * i, n, i):
                primeList[j] = False

    value = 0
    for i in range(2, n):
        if primeList[i]:
            if (isSquare(A[i])):
                value += A[i]

    return value