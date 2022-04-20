import sys
import heapq

n = int(input())
total = 0
heap = []
for _ in range(n):
    heapq.heappush(heap, int(sys.stdin.readline()))
while len(heap) > 1:
    top1, top2 = heapq.heappop(heap), heapq.heappop(heap)
    total += top1 + top2
    heapq.heappush(heap, top1 + top2)
print(total)