import sys

t = int(sys.stdin.readline())
for _ in range(t):
    s = int(sys.stdin.readline())
    n = int(sys.stdin.readline())
    for _ in range(n):
        a, b = map(int, sys.stdin.readline().split())
        s += a * b
    print(s)