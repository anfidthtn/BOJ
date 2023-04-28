import math
a, b = map(int, input().split())
c, d = map(int, input().split())
e = a * d + b * c
f = b * d
e, f = e // math.gcd(e, f), f // math.gcd(e, f)
print(e, f)