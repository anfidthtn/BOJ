input()
a=list(map(int,input().split()))
a.sort()
b=list(map(int,input().split()))
b.sort()
c=[]
bi = 0
ai = 0
while ai < len(a):
    if bi >= len(b):
        c.append(a[ai])
        ai += 1
    else:
        if a[ai] < b[bi]:
            c.append(a[ai])
            ai += 1
        elif a[ai] == b[bi]:
            ai += 1
            bi += 1
        else:
            bi += 1

print(len(c))
print(*c)