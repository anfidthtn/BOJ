c = int(input())
m = 0
for _ in range(c):
  s = input()
  count = s.count("for")
  count += s.count("while")
  m = max(m, count)
print(m)