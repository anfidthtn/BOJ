import sys

l = int(input())
s = [0] + list(map(int, input().split()))
s.sort()
n = int(input())
if n in s:
    print(0)
    exit()
idx = 1
while s[idx] < n:
    idx += 1
print((s[idx] - n) * (n - s[idx - 1]) - 1)