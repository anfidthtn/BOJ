n = int(input())

pib = [0, 1, 1]
for i in range(3, n + 1):
    pib.append(pib[i - 2] + pib[i - 1])
print(pib[n])