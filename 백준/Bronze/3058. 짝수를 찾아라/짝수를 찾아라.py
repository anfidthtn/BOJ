t = int(input())
for _ in range(t):
  l = list(map(int, input().split()))
  m = 31203
  s = 0
  for a in l:
    if a % 2 == 0:
      m = min(m, a)
      s += a
  print(s, m, sep=" ")