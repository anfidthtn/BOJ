fib = [-1, 1, 2]
a=10**100
while fib[-1] < a:
    fib.append(fib[-1] + fib[-2])
fib.append(fib[-1] + fib[-2])

def getcount(x):
    left = 0
    right = len(fib) - 1
    ans = -1
    while left <= right:
        mid = (left + right) >> 1
        if fib[mid] <= x:
            ans = mid
            left = mid + 1
        else:
            right = mid - 1
    return ans

while True:
    s = input()
    if s == '0 0':
        break
    a, b = map(int, s.split())
    print(getcount(b) - getcount(a - 1))