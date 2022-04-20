import sys

t = int(sys.stdin.readline())
nums = [[i for i in range(15)]]
for row in range(1, 15):
    nums.append([0] * 15)
    for col in range(1, 15):
        nums[row][col] = nums[row - 1][col] + nums[row][col - 1]
for _ in range(t):
    k = int(sys.stdin.readline())
    n = int(sys.stdin.readline())
    print(nums[k][n])