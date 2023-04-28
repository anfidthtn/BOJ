a = int(input())
s = []
while a:
    s.append(a % 2)
    a //= 2
ans = 0
for n in s:
    ans <<= 1
    ans += n
print(ans)