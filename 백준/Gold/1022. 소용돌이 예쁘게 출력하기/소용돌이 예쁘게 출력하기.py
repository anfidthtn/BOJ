r1, c1, r2, c2 = list(map(int, input().split()))
'''
( n, n) => (2*n+1)**2 = 4*(n**2) + 4*n + 1 = a
(-n, n) => = a - 6n = 4*(n**2) - 2*n + 1
(-n,-n) => = a - 4n = 4*(n**2) + 1
( n,-n) => (2*n+1)**2 - 2*n = 4*(n**2) + 2*n + 1 = a - 2n
'''


def abs(x):
    if x >= 0:
        return x
    return -x


def max(a, b):
    if a >= b:
        return a
    return b


def val(r, c):
    n = max(abs(r), abs(c))
    if r >= c:
        num = 4*(n**2) + 2*n + 1 + r + c
        return num
    else:
        num = 4*(n**2) - 2*n + 1 - r - c
        return num


def getDigit(x):
    digit = 0
    while x > 0:
        digit += 1
        x = int(x/10)
    return digit


def printNum(x, maxdigit):
    digit = getDigit(x)
    for _ in range(maxdigit - digit):
        print(' ', end='')
    print(x, end=' ')


maxDigit = getDigit(val(r1, c1))
maxDigit = max(maxDigit, getDigit(val(r1, c2)))
maxDigit = max(maxDigit, getDigit(val(r2, c1)))
maxDigit = max(maxDigit, getDigit(val(r2, c2)))

for i in range(r2 - r1 + 1):
    for j in range(c2 - c1 + 1):
        printNum(val(r1 + i, c1 + j), maxDigit)
    print()