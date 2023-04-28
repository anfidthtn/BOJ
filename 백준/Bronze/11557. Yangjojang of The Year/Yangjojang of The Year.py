t=int(input())
for _ in range(t):
    n = int(input())
    d = {}
    for _ in range(n):
        a, b = input().split()
        b = int(b)
        d[b] = a
    print(d[max(d)])