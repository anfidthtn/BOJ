import sys

sys.setrecursionlimit(10 ** 6)

N = int(input())

def bs(num, left, right):
  if left == right:
    return left
  
  mid = (left + right) // 2
  mid2 = mid * mid
  if mid2 == num:
    return mid
  elif num < mid2:
    return bs(num, left, mid - 1)
  else:
    return bs(num, mid + 1, right)

print(bs(N, 1, 10 ** 800))