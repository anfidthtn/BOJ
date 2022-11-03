a, b = map(int, input().split())
ca = [False for _ in range(a)]
cb = [False for _ in range(b)]
for aa in range(a):
  s = input()
  for bb in range(b):
    if s[bb] == 'X':
      ca[aa] = True
      cb[bb] = True

cca = ca.count(False)
ccb = cb.count(False)
print(max(cca, ccb))