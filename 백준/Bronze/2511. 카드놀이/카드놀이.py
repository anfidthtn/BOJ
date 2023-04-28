a=list(map(int,input().split()))
b=list(map(int,input().split()))
ac = 0
bc = 0
finalwin = None
for i in range(10):
    if a[i] < b[i]:
        bc+=3
        finalwin = "B"
    elif a[i] > b[i]:
        ac +=3
        finalwin = "A"
    else:
        ac += 1
        bc += 1

print(ac, bc)
if ac < bc:
    print("B")
elif ac > bc:
    print("A")
elif finalwin == None:
    print("D")
else:
    print(finalwin)