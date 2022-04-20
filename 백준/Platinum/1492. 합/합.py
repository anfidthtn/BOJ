N, K = map(int, input().split())

MODULAR = 10 ** 9 + 7

# power 에 MODULAR - 2 들어오면 모듈러 곱셈 역원
def getPowerMod(num, power):
    result = 1
    while power > 0:
        if power % 2 != 0:
            result *= num
            result %= MODULAR
        num *= num
        num %= MODULAR
        power = int(power / 2)
    return result

def getCombination(n, r):
    return (fact[n] * getPowerMod((fact[n - r] * fact[r]) % MODULAR, MODULAR - 2)) % MODULAR

powerSum = [N]
fact = [1]
for i in range(1, K + 2):
    fact.append((fact[i - 1] * i) % MODULAR)

for k in range(1, K + 1):
    nextPowerSum = getPowerMod(N + 1, k + 1) - 1
    for i in range(0, k):
        nextPowerSum -= (powerSum[i] * getCombination(k + 1, i)) % MODULAR
        nextPowerSum %= MODULAR
    nextPowerSum = (nextPowerSum * getPowerMod(getCombination(k + 1, k), MODULAR - 2)) % MODULAR
    powerSum.append(nextPowerSum)
print(powerSum[K])