a=int(input())
b=int(input())
if a == 1:
    print("Before")
elif a == 2:
    if b < 18:
        print("Before")
    elif b == 18:
        print("Special")
    else:
        print("After")
else:
    print("After")