import math

countL = [0, ]

for i in range(1, 11):
    countL.append(int(math.factorial(10) / math.factorial(i) / math.factorial(10 - i)))


N = int(input())
numSize = 0

for i in range(1, 11):
    if countL[i] <= N:
        N -= countL[i]
    else:
        numSize = i
        break


if numSize == 0:
    print(-1)
    exit(0)


temp = [0] * numSize

def tempInit(numSize):
    for i in range(numSize):
        global temp
        temp[i] = i


tempInit(numSize)


while N > 0:
    for i in range(numSize):
        if i == numSize - 1 or temp[i] < temp[i + 1] - 1:
            temp[i] += 1
            tempInit(i)
            N -= 1
            break


temp.reverse()

answer = 0
for i in temp:
    answer *= 10
    answer += i


print(answer)