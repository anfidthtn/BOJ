a, p = map(int, input().split())
ansset = set()
ansset.add(a)
d = [a]

def getNext(x):
    next = 0
    while x:
        next += (x % 10) ** p
        x //= 10
    return next

while True:
    a = getNext(a)
    if a in ansset:
        break
    ansset.add(a)
    d.append(a)
print(d.index(a))