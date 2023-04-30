import sys
n = int(input())
a = [0 for _ in range(n)]
for _ in range(n):
    a[_] = int(sys.stdin.readline())
a.sort(reverse=True)
print(sum(a) - sum(a[2::3]))