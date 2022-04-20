import sys
n, m = map(int, input().split())
nums = list(map(int, input().split()))
sums = [nums[0]]
for i in range(1, n):
    sums.append(sums[-1] + nums[i])

for _ in range(m):
    i, j = map(int, sys.stdin.readline().split())
    print(sums[j - 1] - sums[i - 1] + nums[i - 1])