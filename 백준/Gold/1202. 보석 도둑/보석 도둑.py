import sys
import heapq
n, k = map(int, sys.stdin.readline().split())

class info:
    weight = 0
    value = 0

    def __init__(self, weight, value):
        self.weight = weight
        self.value = value
    
    def getWeight(self):
        return self.weight

infoW = []
for _ in range(n):
    temp = list(map(int, sys.stdin.readline().split()))
    infoW.append(info(temp[0], temp[1]))
infoW.sort(key=info.getWeight)

bag = []
for _ in range(k):
    bag.append(int(sys.stdin.readline()))
bag.sort()

pq = []
heapq.heapify(pq)

total = 0
idx = 0

for limit in bag:
    while idx < len(infoW) and infoW[idx].weight <= limit:
        heapq.heappush(pq, - infoW[idx].value)
        idx += 1
    if (len(pq) == 0):
        continue
    total -= heapq.heappop(pq)
print(total)