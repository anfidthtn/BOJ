t = int(input())
l = [1, 1, 2, 4]
for i in range(4, 50001):
  l.append(0)
  l[i] += l[i - 1]
  l[i] += l[i - 2]
  l[i] += l[i - 3]
  l[i] %= 1_000_000_009

for _ in range(t):
  n = int(input())
  if n == 1:
    print(1)
    continue
  print((l[n // 2] + l[n // 2 - 1]) % 1_000_000_009)