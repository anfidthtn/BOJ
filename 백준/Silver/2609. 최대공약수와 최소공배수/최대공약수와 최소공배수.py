def GCD(a, b = None):
    if b is None:
        return a
    while b > 0:
        temp = a % b
        a = b
        b = temp
    return a

def LCM(a, b = None):
    if b is None:
        return a
    return a * b // GCD(a, b)

a, b = map(int, input().split())
print(GCD(a, b))
print(LCM(a, b))