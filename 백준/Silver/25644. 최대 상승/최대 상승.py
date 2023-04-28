n = int(input())
nums = [0] + list(map(int,input().split())) + [0]
mins = [1 << 40 for _ in range(len(nums))]
maxs = [0 for _ in range(len(nums))]
for i in range(1, n + 1):
    mins[i] = min(nums[i], mins[i - 1])
for i in range(n, 0, -1):
    maxs[i] = max(nums[i], maxs[i + 1])
ans = 0
for i in range(1, n + 1):
    ans = max(ans, maxs[i] - mins[i - 1])
print(ans)