while True:
  n = int(input())
  if n == -1:
    break
  s = 0
  ans = []
  for i in range(1, n):
    if n % i == 0:
      s += i
      ans.append(i)
  if s == n:
    print(str(n) + ' = ', end='')
    print(*ans, sep=' + ')
  else:
    print(str(n) + ' is NOT perfect.')