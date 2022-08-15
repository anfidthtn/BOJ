n = int(input())
r = [0] + list(map(int, input().split()))
s = [0] * (n + 1)
for i in range(1, n + 1):
    if r[i] == 1:
        s[i] = s[i - 1] + r[i]
print(sum(s))