A, B, N = map(int, input().split())

def power(n, p):
  res = 1
  while p > 0:
    if p % 2 == 1:
      res *= n
    n *= n
    p >>= 1
  return res

A *= power(10, N + 1)

print(((A // B) % 100) // 10)