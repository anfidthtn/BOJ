a = int(input())
b = int(input())
c = int(input())
d = int(input())
e = int(input())

t = 0
while a < 0:
  a += 1
  t += c
if a == 0:
  t += d
while a < b:
  a += 1
  t += e
print(t)