n = int(input())
m = int(input())
nums = list(map(int, input().split()))
ans = max(nums[0], n - nums[-1])
for i in range(1, m):
    ans = max(ans, (nums[i] - nums[i - 1] + 1) // 2)
print(ans)