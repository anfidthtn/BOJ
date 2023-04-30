import sys
n = int(input())
ans = 0
nums = [0 for _ in range(n)]
for i in range(0, n):
    nums[i] = int(sys.stdin.readline())
nums.sort(reverse=True)
for i in range(1, n + 1):
    x = nums[i - 1] - i + 1
    ans += x if x >= 0 else 0

print(ans)