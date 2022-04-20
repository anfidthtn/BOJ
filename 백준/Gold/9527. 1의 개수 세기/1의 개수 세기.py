A, B = map(int, input().split())

def getSumOne(n):
    sum = 0
    shift = 0
    while n >> shift > 0:
        sum += (2 ** shift) * (n >> (shift + 1))
        sum += ((n >> shift) & 1) * ((((2 ** shift) - 1) & n) + 1)
        shift += 1

    return sum

print(getSumOne(B) - getSumOne(A - 1))