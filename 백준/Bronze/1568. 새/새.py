n = int(input())
t = 0
x = 1
while(n > 0):
  if x > n:
    x = 1
    continue
  n -= x
  t += 1
  x += 1

print(t)