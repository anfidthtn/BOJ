import sys
n = int(input())
total = 0
count = [0] * 8001

for _ in range(n):
    num = int(sys.stdin.readline())
    count[num + 4000] += 1
    total += num

avg = total * 10
avg //= n
if avg % 10 >= 5:
    avg += 10
avg //= 10
print(avg)

find = n // 2 + 1
for i in range(-4000, 4001):
    find -= count[i + 4000]
    if find <= 0:
        print(i)
        break

modeValue = 1
modeIndex = []
for i in range(-4000, 4001):
    if count[i + 4000] == modeValue:
        modeIndex.append(i)
    elif count[i + 4000] > modeValue:
        modeValue = count[i + 4000]
        modeIndex = [i]
if len(modeIndex) > 1:
    print(modeIndex[1])
else:
    print(modeIndex[0])

for i in range(-4000, 4001):
    if count[i + 4000] > 0:
        minValue = i
        break
for i in range(4000, -4001, -1):
    if count[i + 4000] > 0:
        maxValue = i
        break
print(maxValue - minValue)