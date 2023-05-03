import sys

d = {}
def getNext(x):
    if x < 10:
        return
    if x in d:
        return d[x]
    ans = 1
    n = x
    while n > 0:
        ans *= n % 10
        n //= 10
    d[x] = ans
    return ans

while True:
    n = int(sys.stdin.readline())
    if n == 0:
        break
    while n >= 10:
        print(n, end=' ')
        n = getNext(n)
    print(n)