n = int(input())
nums = list(map(int, input().split()))

counts = [0 for _ in range(1001)]
for num in nums:
    counts[num] += 1

for i in range(1, 1001):
    counts[i] += counts[i - 1]

anss = [0 for _ in range(n)]
for i in range(n - 1, -1, -1):
    anss[i] = counts[nums[i]] - 1
    counts[nums[i]] -= 1
print(*anss)