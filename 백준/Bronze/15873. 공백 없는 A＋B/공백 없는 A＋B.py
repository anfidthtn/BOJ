ab = input()
if len(ab) == 2:
    print(int(ab[0]) + int(ab[1]))
elif len(ab) == 3:
    if int(ab[1]) == 0:
        print(int(ab[:2]) + int(ab[2]))
    else:
        print(int(ab[0]) + int(ab[1:]))
else:
    print(int(ab[:2]) + int(ab[2:]))