def GCD(a, b = None):
    if b is None:
        return a
    while b > 0:
        temp = a % b
        a = b
        b = temp
    return a

n, m = map(int, input().split())

if n == 0 or m == 0:
    print((n + m) ** 2)
else:
    gcd = GCD(n, m)
    n //= gcd
    m //= gcd
    if (n + m) % 2 == 0:
        print(2 * gcd ** 2)
    else:
        print(gcd ** 2)