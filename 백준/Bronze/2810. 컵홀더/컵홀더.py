n = int(input())
s = input()
isll = False
i = 0
c = n
while i < n:
  if s[i] == 'L':
    if not isll:
      isll = True
    else:
      c -= 1
    i += 2
  else:
    i += 1

print(c)