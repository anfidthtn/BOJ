import sys

n = int(sys.stdin.readline())

count = (6, 2, 5, 5, 4, 5, 6, 3, 7, 6)

def getCount(num):
    return count[num // 10] + count[num % 10]

n -= 4

for sumNum in range(100):
    for a in range(sumNum + 1):
        if getCount(a) + getCount(sumNum - a) + getCount(sumNum) == n:
            print(format(a, '02'), '+', format(sumNum - a, '02'), '=', format(sumNum, '02'), sep='')
            exit()
print('impossible')