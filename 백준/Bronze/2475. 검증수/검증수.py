l = list(map(int, input().split()))

s = 0
for x in l:
    s += x**2

print(s%10)