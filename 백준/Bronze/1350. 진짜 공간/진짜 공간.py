import math
n = int(input())
ans = 0
nums = list(map(int,input().split()))
x=int(input())
for num in nums:
    ans += math.ceil(num / x) * x
print(ans)