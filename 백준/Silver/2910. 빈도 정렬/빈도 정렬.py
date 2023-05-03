n, c = map(int, input().split())
nums = list(map(int, input().split()))
counts = {}
fIdx = {}
for i in range(n):
    num = nums[i]
    if num not in counts:
        counts[num] = 0
        fIdx[i] = num
    counts[num] += 1

total = 0
while total < n:
    now = max(counts.values())
    for i in range(n):
        if i not in fIdx:
            continue
        if counts[fIdx[i]] == now:
            for k in range(counts[fIdx[i]]):
                print(fIdx[i], end=' ')
            total += counts[fIdx[i]]
            counts.pop(fIdx[i])
            fIdx.pop(i)