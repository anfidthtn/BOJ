n = int(input())

sqn = int(n ** 0.5) + 1

for i in range(2, sqn + 1):
    while n % i == 0:
        print(i)
        n //= i
if n > 1:
    print(n)