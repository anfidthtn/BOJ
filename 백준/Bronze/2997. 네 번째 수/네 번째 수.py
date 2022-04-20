nums = list(map(int, input().split()))
nums.sort()
diff1 = nums[1] - nums[0]
diff2 = nums[2] - nums[1]
if diff1 == diff2 * 2:
    print(nums[0] + diff2)
elif diff1 * 2 == diff2:
    print(nums[1] + diff1)
else:
    print(nums[2] + diff1)