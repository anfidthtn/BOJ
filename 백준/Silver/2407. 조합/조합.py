fact = [1]
n, m = map(int, input().split())

for i in range(1, n + 1):
    fact.append(fact[i - 1] * i)

print(fact[n] // (fact[n - m] * fact[m]))