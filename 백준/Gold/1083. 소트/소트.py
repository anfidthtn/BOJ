N = int(input())
nums = list(map(int, input().split()))
S = int(input())

nowIdx = 0
while S > 0 and nowIdx < len(nums):
    maxValue = max(nums[nowIdx : nowIdx + S + 1])
    maxIdx = nums.index(maxValue)
    for i in range(maxIdx, nowIdx, -1):
        nums[i - 1], nums[i] = nums[i], nums[i - 1]
        S -= 1
    nowIdx += 1

for i in nums[:-1]:
    print(i, end=' ')

print(nums[-1])