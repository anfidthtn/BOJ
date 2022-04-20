def rev(x):
    result = 0
    while x > 0:
        result *= 10
        result += x % 10
        x //= 10
    return result


a, b = map(int, input().split())
print(rev(rev(a) + rev(b)))