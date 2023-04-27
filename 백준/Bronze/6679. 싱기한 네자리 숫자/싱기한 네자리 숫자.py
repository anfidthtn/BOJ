def find(x):
    n10 = 0
    n12 = 0
    n16 = 0
    n = x
    while n > 0:
        n10 += n % 10
        n //= 10
    n = x
    while n > 0:
        n12 += n % 12
        n //= 12
    if n10 != n12:
        return False
    n = x
    while n > 0:
        n16 += n % 16
        n //= 16
    if n12 != n16:
        return False
    return True

for i in range(1000, 10000):
    if find(i):
        print(i)