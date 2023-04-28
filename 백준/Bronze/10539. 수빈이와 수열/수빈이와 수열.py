n=int(input())
nums = list(map(int,input().split()))
beforesum = 0
for i in range(n):
    nowsum = nums[i] * (i + 1)
    print(nowsum - beforesum, end=' ')
    beforesum = nowsum