import sys

N, M = map(int, sys.stdin.readline().split())

Parent = [0] + [-1] * N

Truth = list(map(int, sys.stdin.readline().split()))
Truth = Truth[1:]


def getLoot(person):
    while Parent[person] > 0:
        person = Parent[person]
    return person


Party = []

for _ in range(M):
    temp = list(map(int, sys.stdin.readline().split()))

    Party.append(temp[1:])
    for i in temp[2:]:
        if Parent[i] != -1:
            if getLoot(i) != getLoot(temp[1]):
                Parent[getLoot(i)] = getLoot(temp[1])
                Parent[i] = getLoot(temp[1])
        else:
            if getLoot(temp[1]) != i:
                Parent[i] = getLoot(temp[1])

TruthLoot = list(set([getLoot(x) for x in Truth]))

count = 0
for p in Party:
    if len(p) > 0:
        isFound = False
        for i in TruthLoot:
            if getLoot(p[0]) == i:
                isFound = True
                break
        if isFound is False:
            count += 1
    else:
        count += 1

print(count)