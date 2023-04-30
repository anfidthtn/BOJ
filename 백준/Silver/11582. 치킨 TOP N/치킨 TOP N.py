n = int(input())
nums = list(map(int,input().split()))
k = int(input())
for i in range(0, n, n // k):
    print(*sorted(nums[i:i + n // k]), end=' ')