n = int(input())
nums = list(map(int, input().split()))
nums.sort()

# 같은 수끼리 얕은 복사로 True False여부 묶이게 조절
isGood = [[False]] + [None] * (n - 1)
for i in range(1, n):
    if nums[i] == nums[i - 1]:
        isGood[i] = isGood[i - 1]
    else:
        isGood[i] = [False]
    

def isExist(numList, num, left, right, exceptIdxA, exceptIdxB):
    if right < left:
        return [False]
    if numList[(left + right) >> 1] == num:
        if isGood[(left + right) >> 1][0] is True:
            return [False]
        if (left + right) >> 1 != exceptIdxA and (left + right) >> 1 != exceptIdxB:
            return [True, (left + right) >> 1]
        else:
            leftReturn = isExist(numList, num, left, ((left + right) >> 1) - 1, exceptIdxA, exceptIdxB)
            if leftReturn[0] is True:
                return leftReturn
            rightReturn = isExist(numList, num, ((left + right) >> 1) + 1, right, exceptIdxA, exceptIdxB)
            return rightReturn
    elif numList[(left + right) >> 1] < num:
        return isExist(numList, num, ((left + right) >> 1) + 1, right, exceptIdxA, exceptIdxB)
    else:
        return isExist(numList, num, left, ((left + right) >> 1) - 1, exceptIdxA, exceptIdxB)

for i in range(n):
    for j in range(i + 1, n):
        info = isExist(nums, nums[i] + nums[j], 0, n - 1, i, j)
        if info[0] is True:
            isGood[info[1]][0] = True

count = 0
for i in range(n):
    if isGood[i][0] is True:
        count += 1
print(count)