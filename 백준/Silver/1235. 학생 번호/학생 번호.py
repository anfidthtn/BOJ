n = int(input())
s = [0 for _ in range(n)]
ans = 0
for i in range(n):
    kkk = input()
    ans = len(kkk)
    s[i] = int(kkk[::-1])

def check():
    m = set(s)
    if len(m) == n:
        return True
    return False


for k in range(ans, 0, -1):
    if check():
        ans = k
    else:
        break
    for i in range(n):
        s[i] //= 10
print(ans)