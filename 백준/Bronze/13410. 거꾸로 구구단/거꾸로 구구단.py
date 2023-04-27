import sys
input = sys.stdin.readline
a, b = map(int, input().split())
temp = set()
for j in range(1, b + 1):
    temp.add(a * j)
ans = []
for num in temp:
    a = 0
    while num > 0:
        a *= 10
        a += num % 10
        num //= 10
    ans.append(a)
print(max(ans))