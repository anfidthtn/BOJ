n = int(input())
nums = list(map(int, input().split()))
prime = [2]
for i in range(3, 1001):
    isValid = True
    for p in prime:
        if i % p == 0:
            isValid = False
            break
    if isValid is True:
        prime.append(i)

count = 0
for num in nums:
    if num in prime:
        count += 1

print(count)