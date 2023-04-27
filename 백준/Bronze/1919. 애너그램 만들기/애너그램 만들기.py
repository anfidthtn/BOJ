a = input()
b = input()
counta=[0 for _ in range(200)]
countb=[0 for _ in range(200)]
for c in a:
    counta[ord(c)] += 1
for c in b:
    countb[ord(c)] += 1

ans = 0
for i in range(0, 200):
    ans += abs(counta[i] - countb[i])
print(ans)