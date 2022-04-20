a, b, c = list(map(int, input().split()))
d = int(input())

c += d
b += c // 60
c %= 60
a += b // 60
b %= 60
a %= 24
print(a, b, c, sep=' ')