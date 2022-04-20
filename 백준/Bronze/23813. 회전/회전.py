N = list(input())
sum = 0
for num in N:
    sum += int(num)
print(int('1' * len(N)) * sum)
