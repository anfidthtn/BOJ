a, b = map(int, input().split())

isPrime = [False, False] + [True] * (int(b ** 0.5))
count = 0
for num in range(2, int(b ** 0.5) + 1):
    if isPrime[num] is False:
        continue
    for i in range(num * 2, int(b ** 0.5) + 1, num):
        isPrime[i] = False
    expo = 2
    while num ** expo <= b:
        if num ** expo < a:
            expo += 1
            continue
        count += 1
        expo += 1
print(count)