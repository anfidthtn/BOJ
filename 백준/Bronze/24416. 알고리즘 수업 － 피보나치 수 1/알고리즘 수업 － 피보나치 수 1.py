n = int(input())
fibb = [0, 1, 1]
for i in range(3, 41):
  fibb.append(fibb[-2] + fibb[-1])

print(fibb[n], n - 2)