n = int(input())
nums = list(map(int, input().split()))
odd = []
even = []
for num in nums:
    if num % 2 == 0:
        even.append(num)
    else:
        odd.append(num)

print(1 if len(nums) % 2 == 1 and len(even) + 1 == len(odd) or len(nums) % 2 == 0 and len(even) == len(odd) else 0)