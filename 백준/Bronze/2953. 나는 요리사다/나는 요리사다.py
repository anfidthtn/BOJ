M = 0
s = 0
for i in range(5):
  ts = sum(map(int, input().split()))
  if (s < ts):
    M = i + 1
    s = ts
print(M, s, sep=" ")