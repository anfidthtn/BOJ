import sys
t = int(sys.stdin.readline())

cycle = 45 # 0 ~ 9

def getDigitSum(num):
    if num <= 0:
        return 0
    count = 0
    now = 16
    while num // 10 ** now <= 0:
        now -= 1
    for power in range(now, -1, -1):
        digit = num // 10 ** power
        for i in range(digit):
            count += i * (10 ** power)
            if power > 0:
                count += 45 * power * (10 ** (power - 1))
        count += digit * ((num % (10 ** power)) + 1)
        num %= 10 ** power
    return count

for _ in range(t):
    a, b = map(int, sys.stdin.readline().split())
    print(getDigitSum(b) - getDigitSum(a - 1))