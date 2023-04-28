n = int(input())
nums = list(map(int,input().split()))
nums.sort()
ans = 0
for i in range(n):
    ans = max(ans, i + 2 + nums[- i - 1])

print(ans)