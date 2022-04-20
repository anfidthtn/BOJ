D, N = map(int, input().split())
ovenList = list(map(int, input().split()))
pizzaList = list(map(int, input().split()))

if D < N:
    print(0)
    exit(0)


for slot in range(1, len(ovenList)):
    if ovenList[slot - 1] < ovenList[slot]:
        ovenList[slot] = ovenList[slot - 1]


searchIdx = len(ovenList) - 1
for pizza in pizzaList:
    if searchIdx < 0:
        print(0)
        exit(0)
    while pizza > ovenList[searchIdx]:
        searchIdx -= 1
        if searchIdx < 0:
            print(0)
            exit(0)
    searchIdx -= 1

print(searchIdx + 2)