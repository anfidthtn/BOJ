import sys

MAXNUM = 10 ** 9 + 7
def getModInverse(x):
    modInverse = 1
    expo = MAXNUM - 2
    while expo > 0:
        if expo % 2 != 0:
            modInverse *= x
            modInverse %= MAXNUM
        x *= x
        x %= MAXNUM
        expo = int(expo / 2)
    return modInverse

def GCD(a, b = None):
    if b is None:
        return a
    while b > 0:
        temp = a % b
        a = b
        b = temp
    return a
    
m = int(sys.stdin.readline())

result = 0
for _ in range(m):
    n, s = map(int, sys.stdin.readline().split())
    gcd = GCD(n, s)
    n //= gcd
    s //= gcd
    result += s * getModInverse(n)
    result %= MAXNUM
print(result)