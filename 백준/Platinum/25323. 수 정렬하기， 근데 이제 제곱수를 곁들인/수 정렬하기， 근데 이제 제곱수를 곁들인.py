import math

n = int(input())
nums = list(map(int, input().split()))

def sq_check(x: int):
  left = 1
  right = 1_000_000_000_000_000_000
  while left <= right:
    mid = (left + right) >> 1
    mid2 = mid * mid
    if mid2 == x:
      return True
    elif x < mid2:
      right = mid - 1
    else:
      left = mid + 1
  return False

def check(nums: list):
  snums = sorted(nums)
  for i in range(n):
    if not sq_check(nums[i] * snums[i]):
      return False
  return True

print("YES" if check(nums) else "NO")