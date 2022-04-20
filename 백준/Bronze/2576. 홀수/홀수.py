isExist = False
sum = 0
minNum = 100
for _ in range(7):
    num = int(input())
    if num % 2 == 1:
        isExist = True
        sum += num
        if minNum > num:
            minNum = num
if isExist is True:
    print(sum)
    print(minNum)
else:
    print(-1)