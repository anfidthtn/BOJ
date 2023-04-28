a=list(map(int,input().split()))
b=list(map(int,input().split()))
ac = 0
bc = 0
for i in range(10):
    if a[i] < b[i]:
        bc+=1
    elif a[i] > b[i]:
        ac += 1

if ac == bc:
    print("D")
elif ac < bc:
    print("B")
else:
    print("A")