N = list(input())
Mul16 = N.copy()
Mul16.extend([0, 0, 0, 0])
N.reverse()
Mul16.reverse()
Mul1 = N.copy()
Mul1.extend([0, 0, 0, 0])
result = [0] * (len(Mul1) + 1)
for i in range(len(Mul1)):
    result[i + 1] = int((result[i] + int(Mul16[i]) + int(Mul1[i])) / 2)
    result[i] = (result[i] + int(Mul16[i]) + int(Mul1[i])) % 2

result.reverse()
for i in range(result.index(1), len(result)):
    print(result[i], end='')