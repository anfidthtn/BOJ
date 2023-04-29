s=input()
ss = []
for i in range(len(s)):
    ss.append(s[i:])

ss.sort()
print(*ss, sep='\n')