import sys

x = list(sys.stdin.readline().rstrip())

def getDigitSumList(numList):
    result = 0
    for num in numList:
        result += int(num)
    return list(str(result))

count = 0
while len(x) > 1:
    x = getDigitSumList(x)
    count += 1
print(count)
if int(x[0]) % 3 == 0:
    print('YES')
else:
    print('NO')