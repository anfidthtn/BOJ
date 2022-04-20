import sys
t = int(sys.stdin.readline())
fact = [1]
for _ in range(t):
    n, m = map(int, sys.stdin.readline().split())
    while len(fact) <= m:
        fact.append(fact[-1] * len(fact))
    print(fact[m] // (fact[m - n] * fact[n]))