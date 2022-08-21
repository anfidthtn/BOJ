num = int(input())
for i in range(65):
  if ((2 ** i) - 1) * 2 ** (64 - i) == num:
    print(i)