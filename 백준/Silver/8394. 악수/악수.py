n = int(input())
pib = [0 for _ in range(10000005)]
pib[0] = 1
pib[1] = 1
pib[2] = 2
pib[3] = 3
for i in range(4, n + 1):
    pib[i] = pib[i - 1] + pib[i - 2]
    pib[i] %= 10
print(pib[n])