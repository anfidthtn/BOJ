n=int(input())
nums = list(map(int, input().split()))
nums.sort()
ans = 1 << 30
for i in range(n):
    ans = min(ans, nums[i] + nums[-(1 + i)])
print(ans)