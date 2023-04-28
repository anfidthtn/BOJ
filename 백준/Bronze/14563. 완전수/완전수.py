n = int(input())
for num in map(int, input().split()):
    s = 0
    for i in range(1, num):
        if num % i == 0:
            s += i
    if s < num:
        print("Deficient")
    elif s > num:
        print("Abundant")
    else:
        print("Perfect")