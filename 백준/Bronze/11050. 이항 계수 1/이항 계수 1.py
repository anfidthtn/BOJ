fact = [1]
for i in range(1, 11):
    fact.append(fact[i - 1] * i)

n, k = map(int, input().split())
print(fact[n] // (fact[k] * fact[n - k]))