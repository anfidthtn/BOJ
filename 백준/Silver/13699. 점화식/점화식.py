t=[1, 1]
n = int(input())
while len(t) <= n:
    idx = len(t)
    next = 0
    for i in range(idx):
        next += t[i] * t[-i -1]
    t.append(next)
print(t[n])