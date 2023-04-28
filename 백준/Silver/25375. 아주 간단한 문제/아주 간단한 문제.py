import sys
q = int(input())
for _ in range(q):
    a, b = map(int,sys.stdin.readline().split())
    print(1 if b % a == 0 and b >= 2 * a else 0)