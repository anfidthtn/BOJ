i = int(input())
y = 2018
m = 4

while y < i:
  y += 2
  m += 2
  if m > 12:
    m -= 12
    y += 1

if y == i:
  print("yes")
else:
  print("no")