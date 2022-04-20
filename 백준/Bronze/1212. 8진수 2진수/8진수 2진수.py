import sys

nums = list(input())

result = []
for num in nums:
    num = int(num)
    result.append((num // 4) % 2)
    result.append((num // 2) % 2)
    result.append((num // 1) % 2)
try:
    for i in range(result.index(1), len(result)):
        print(result[i], end='')
except:
    print(0)
