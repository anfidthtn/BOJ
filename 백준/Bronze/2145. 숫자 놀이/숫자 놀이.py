def getNum(n):
  if n < 10:
    return n
  num = 0
  while n > 0:
    num += n % 10
    n //= 10
  return getNum(num)

while True:
  n = int(input())
  if n == 0:
    break
  print(getNum(n))