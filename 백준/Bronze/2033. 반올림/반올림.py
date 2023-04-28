mul = 1
n = int(input())
while n >= 10:
    if n % 10 >= 5:
        n += 10
    n //= 10
    mul *= 10

print(n * mul)