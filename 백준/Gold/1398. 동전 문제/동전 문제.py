import sys
t = int(sys.stdin.readline())
MAXIMUM = 100
minCount = [0] * 100
for i in range(1, 100):
    count1, count10, count25 = MAXIMUM, MAXIMUM, MAXIMUM
    count1 = minCount[i - 1]
    if i >= 10:
        count10 = minCount[i - 10]
    if i >= 25:
        count25 = minCount[i - 25]
    minCount[i] = min(count1, count10, count25) + 1

for _ in range(t):
    cost = int(sys.stdin.readline())
    count = 0
    while cost > 0:
        now = cost % 100
        count += minCount[now]
        cost //= 100
    print(count)