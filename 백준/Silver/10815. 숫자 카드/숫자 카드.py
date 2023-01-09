n = int(input())
nums = sorted(list(map(int, input().split())))
m = int(input())

def binary_search(x: int):
  start = 0
  end = n - 1
  while start <= end:
    mid = (start + end) // 2
    if nums[mid] == x:
      return 1
    elif nums[mid] < x:
      start = mid + 1
    else:
      end = mid - 1
  return 0

for x in map(int, input().split()):
  print(binary_search(x), end=' ')