n = int(input())
nums = [[0, 0, 0]]
for _ in range(n):
  num = int(input())
  nums.append([max(nums[-1]), nums[-1][0] + num, nums[-1][1] + num])

print(max(nums[-1]))