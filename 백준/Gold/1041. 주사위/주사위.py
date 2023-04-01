n = int(input())
a = 0
b = 1
c = 2
d = 3
e = 4
f = 5
diceNum = list(map(int, input().split()))

caseSet = (
    ([a], [b], [c], [d], [e], [f]), # 면 (6면)
    ((a, b), (f, b), (a, c), (f, c), (a, d), (f, d), (a, e), (f, e), (c, b), (d, b), (c, e), (d, e)), # 선 (12선)
    ((a, b, c), (f, b, c), (a, c, e), (f, c, e), (a, e, d), (f, e, d), (a, d, b), (f, d, b)) # 점 (8점)
)

minCase = [10 ** 10] * 3

for i in range(3):
    for case in caseSet[i]:
        tempSum = 0
        for c in case:
            tempSum += diceNum[c]
        minCase[i] = min(minCase[i], tempSum)


if n == 1:
    print(sum(diceNum) - max(diceNum))
elif n == 2:
    print(4 * (minCase[2] + minCase[1]))
else:
    result = 0
    result += 4 * minCase[2]
    result += 4 * (2 * n - 3) * minCase[1] # 위의 4선은 n - 2개, 옆의 4선은 n - 1개
    result += 4 * (n - 2) * (n - 1) * minCase[0]
    result += (n - 2) ** 2 * minCase[0]
    print(result)