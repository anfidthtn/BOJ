n, k = map(int, input().split())
nums = list(map(int, input().split()))
nums.sort()
ans = 0
for i in range(n):
    ans += min(i, k) * nums[i]

print(ans)