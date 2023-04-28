s = input()
ss = set()
for i in range(len(s)):
    for j in range(i + 1, len(s) + 1):
        ss.add(s[i:j])
print(len(ss))