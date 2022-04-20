import sys

M = int(sys.stdin.readline())

MODULAR = 10 ** 9 + 7

def getModInverse(x):
    result = 1
    expo = MODULAR - 2
    while expo > 0:
        if expo % 2 == 1:
            result *= x
            result %= MODULAR
        x *= x
        x %= MODULAR
        expo = int(expo / 2)
    return result

fact = [1]

for _ in range(M):
    N, K = map(int, sys.stdin.readline().split())
    if N >= len(fact):
        for i in range(len(fact), N + 1):
            fact.append((fact[i - 1] * i) % MODULAR)

    print((fact[N] * getModInverse((fact[N - K] * fact[K]) % MODULAR))% MODULAR)