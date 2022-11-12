t = int(input())
for _ in range(t):
  a, b, c, d = map(int, input().split())
  r = a * b - c * d
  if (r > 0):
    print("TelecomParisTech")
  elif (r == 0):
    print("Tie")
  else:
    print("Eurecom")