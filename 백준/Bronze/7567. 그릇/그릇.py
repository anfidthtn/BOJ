s=input()
ans=0
before='c'
for c in s:
  if c != before:
    ans += 10
  else:
    ans += 5
  before = c
print(ans)