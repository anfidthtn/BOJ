a, b = map(int, input().split())


def digitValue(num, m):
    result = 0
    value = 1
    while(num > 0):
        digit = num % 10
        num //= 10
        if digit == 5 or digit == 6:
            digit = m
        result += value * digit
        value *= 10
    return result


print(digitValue(a, 5) + digitValue(b, 5),
      digitValue(a, 6) + digitValue(b, 6), sep=' ')