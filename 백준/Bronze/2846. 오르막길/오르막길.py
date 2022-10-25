n = int(input())
ans = 0
l = list(map(int, input().split()))
top = l[-1]
before = top
for i in range(n - 2, -1, -1):
  if l[i] < before:
    ans = max(top - l[i], ans)
    before = l[i]
  else:
    top = l[i]
    before = l[i]

print(ans)