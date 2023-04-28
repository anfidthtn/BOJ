cam = "CAMBRIDGE"
s = input()
for c in s:
    print(c if c not in cam else '', end='')