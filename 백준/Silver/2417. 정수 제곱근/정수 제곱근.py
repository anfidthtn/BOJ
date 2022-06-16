import math
n = int(input())
q = math.ceil(n ** 0.5)
if q * q < n:
  q += 1
print(q)