n = int(input())
for m in range(n - len(str(n)) * 9, n + 1):
    sum = m
    temp = m
    while m > 0:
        sum += m % 10
        m //= 10
    if sum == n:
        print(temp)
        exit()
print(0)