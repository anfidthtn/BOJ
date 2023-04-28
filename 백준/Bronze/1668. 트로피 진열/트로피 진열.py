n = int(input())
aaa = []
for _ in range(n):
    aaa.append(int(input()))

before = 0
ans = 0
for i in range(n):
    if before < aaa[i]:
        ans += 1
        before = aaa[i]
print(ans)
aaa = aaa[::-1]
before = 0
ans = 0
for i in range(n):
    if before < aaa[i]:
        ans += 1
        before = aaa[i]
print(ans)