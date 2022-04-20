import sys

t = int(sys.stdin.readline())
nums = []
for _ in range(t):
    nums.append(int(sys.stdin.readline()))
nums.sort()
for num in nums:
    print(num)