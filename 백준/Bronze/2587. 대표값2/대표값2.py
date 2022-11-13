a = []
for _ in range(5):
  a.append(int(input()))
print(sum(a) // 5)
a.sort()
print(a[2])