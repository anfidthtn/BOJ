n, b = input().split()
b = int(b)
ans = 0
for c in n:
    ans *= b
    if '0' <= c <= '9':
        ans += int(c)
    else:
        ans += 10 + ord(c) - ord('A')

print(ans)