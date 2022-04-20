n = int(input())
count = 0
expo = 1
while n >= 10 ** expo:
    count += expo * 9 * (10 ** (expo - 1))
    expo += 1
count += expo * (n - 10 ** (expo - 1) + 1)
print(count)