N = int(input())

candiS = [0] * 3
candiV = [[],[],[]]

for _ in range(N):
    score = list(map(int, input().split()))
    for i in range(3):
        candiS[i] += score[i]
        candiV[i].append(score[i])

maxS = max(candiS)

MaxSIndex = []
for idx, Score in enumerate(candiS):
    if Score == maxS:
        MaxSIndex.append(idx)

if len(MaxSIndex) == 1:
    print("{0} {1}".format(MaxSIndex[0] + 1, candiS[MaxSIndex[0]]))
    exit(0)

max3V = 0
for idx in MaxSIndex:
    max3V = max(candiV[idx].count(3), max3V)

Max3VIndex = []
for idx in MaxSIndex:
    if candiV[idx].count(3) == max3V:
        Max3VIndex.append(idx)

if len(Max3VIndex) == 1:
    print("{0} {1}".format(Max3VIndex[0] + 1, candiS[Max3VIndex[0]]))
    exit(0)

max2V = 0
for idx in MaxSIndex:
    max2V = max(candiV[idx].count(2), max2V)

Max2VIndex = []
for idx in MaxSIndex:
    if candiV[idx].count(2) == max2V:
        Max2VIndex.append(idx)

if len(Max2VIndex) == 1:
    print("{0} {1}".format(Max2VIndex[0] + 1, candiS[Max2VIndex[0]]))
    exit(0)

print("0 {}".format(candiS[Max2VIndex[0]]))