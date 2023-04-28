a = []
b = []
for _ in range(10):
    a.append(int(input()))
for _ in range(10):
    b.append(int(input()))
a.sort()
b.sort()
print(sum(a[-3:]), sum(b[-3:]))