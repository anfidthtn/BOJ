a, b = map(int, input().split())
def getSum(num):
    n = 0
    while ((n + 1) * (n + 2)) // 2 < num:
        n += 1
    return (n + 1) * (num - (n * (n + 1)) // 2) + (n * (n + 1) * (2 * n + 1)) // 6

print(getSum(b) - getSum(a - 1))