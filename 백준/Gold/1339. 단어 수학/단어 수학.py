n = int(input())
count = [0] * 26
for _ in range(n):
    nums = list(input())
    nums.reverse()
    for digit, num in enumerate(nums):
        count[ord(num) - ord('A')] += 10 ** digit

count.sort(reverse=True)
maxSum = 0
for i in range(9):
    maxSum += count[i] * (9 - i)
print(maxSum)