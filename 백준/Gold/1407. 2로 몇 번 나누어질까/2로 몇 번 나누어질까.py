def getCount(num):
    result = 0
    expo = 0
    while num >= 2 ** expo:
        result += ((num + 2 ** expo) // (2 ** (expo + 1))) * 2 ** expo
        expo += 1
    return result

a, b = map(int, input().split())
print(getCount(b) - getCount(a - 1))