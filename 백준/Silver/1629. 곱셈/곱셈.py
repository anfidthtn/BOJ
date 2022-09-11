a, b, c = map(int, input().split())
def pow(x, expo):
    result = 1
    while expo > 0:
        if expo % 2 != 0:
            result *= x
            result %= c
        x *= x
        x %= c
        expo //= 2
    return result

print(pow(a, b))