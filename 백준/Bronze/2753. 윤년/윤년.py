a = int(input())
if a % 400 == 0:
    print(1)
elif a % 100 == 0:
    print(0)
elif a % 4 == 0:
    print(1)
else:
    print(0)