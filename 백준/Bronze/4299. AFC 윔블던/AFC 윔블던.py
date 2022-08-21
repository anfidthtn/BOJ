s, a = map(int, input().split())
if s - a < 0:
  print(-1)
elif (s - a) % 2 == 1:
  print(-1)
else:
  print((s - a) // 2 + a, (s - a) // 2, sep=" ")