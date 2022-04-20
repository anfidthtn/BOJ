N = int(input())
heights = list(map(int, input().split()))

MAXIMUM = 1000000001

counts = [0] * N

for i in range(N):
    slope = MAXIMUM
    for j in range(i).__reversed__():
        if slope * (i - j) > heights[i] - heights[j]:
            slope = (heights[i] - heights[j]) / (i - j)
            counts[i] += 1
            counts[j] += 1

maxidx = 0
maxcount = 0
for idx, i in enumerate(counts):
    if maxcount < i:
        maxcount = i
        maxidx = idx

print(counts[maxidx])