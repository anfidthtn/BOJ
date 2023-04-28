n = int(input())
nums = list(map(int,input().split()))
nums.sort()
ans = 0
s = sum(nums)
for i in range(n - 1):
    s -= nums[i]
    ans += nums[i] * s

print(ans)