a, b = map(int, input().split())
c = True
for d in range(2, b):
  if (a % d == 0):
    print("BAD", d, sep=" ")
    c = False
    break
if c:
  print("GOOD")