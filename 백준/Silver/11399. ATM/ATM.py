n = int(input())
tList = list(map(int, input().split()))
tList.sort()
sum = 0
tempSum = 0
for t in tList:
    tempSum += t
    sum += tempSum
print(sum)