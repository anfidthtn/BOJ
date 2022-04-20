N = int(input())

T = N - 1
count = 0

while T > 0:
    T = int(T / 2)
    count += 1

print(count)
T = N - 1

count = 0
while T > 0:
    T = int(T / 2)
    tempList = []
    for i in range(N):
        tempNumList = list(str(bin(i)))[2:]
        if len(tempNumList) < count + 1:
            tempList.append(i + 1)
            continue
        if tempNumList[- count - 1] == '0':
            tempList.append(i + 1)
    print(len(tempList), end=' ')
    for i in tempList:
        print(i, end=' ')
    print()


    count += 1