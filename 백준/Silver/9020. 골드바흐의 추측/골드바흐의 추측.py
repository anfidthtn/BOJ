import sys

t = int(sys.stdin.readline())
prime = [2]
next = 3
for _ in range(t):
    n = int(sys.stdin.readline())
    while prime[-1] <= n:
        isValid = True
        for p in prime:
            if next % p == 0:
                isValid = False
                break
        if isValid is True:
            prime.append(next)
        next += 2
    
    for candi in range(n // 2, 1, -1):
        if candi in prime:
            if n - candi in prime:
                print(candi, n - candi)
                break
