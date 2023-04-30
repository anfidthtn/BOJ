import sys
input = sys.stdin.readline
n = int(input())
d = {}
for _ in range(2 * n - 1):
    x = input().rstrip()
    if x not in d:
        d[x] = 0
    d[x] += 1
for key in d.keys():
    if d[key] & 1 == 1:
        print(key)
        break