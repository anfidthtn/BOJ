N = int(input())


def nextNums(x):
    if x < 10:
        return x * 11
    temp = int(x % 10) + int(x / 10)

    return int(x % 10) * 10 + int(temp % 10)


nums = [N]
cycle = 1
while nextNums(nums[-1]) != nums[0]:
    nums.append(nextNums(nums[-1]))
    cycle += 1

print(cycle)