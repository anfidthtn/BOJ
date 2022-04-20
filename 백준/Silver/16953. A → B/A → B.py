a, b = map(int, input().split())

MAXNUM = 10 ** 10

def getAB(target, num):
    if num == 0:
        return MAXNUM
    if target == num:
        return 1
    a, b = MAXNUM, MAXNUM
    if num % 10 == 1:
        a = getAB(target, num // 10)
    if num % 2 == 0:
        b = getAB(target, num // 2)
    return min(a, b) + 1

count = getAB(a, b)
if count >= MAXNUM:
    print(-1)
else:
    print(count)
