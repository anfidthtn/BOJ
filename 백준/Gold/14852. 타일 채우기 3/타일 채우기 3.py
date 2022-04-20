n = int(input())
mod = 10 ** 9 + 7
case = [1, 2, 7] + [0] * (n - 2)
caseSum = [1, 2, 9] + [0] * (n - 2)
for i in range(3, n + 1):
    case[i] = (2 + case[i - 2] + 2 * caseSum[i - 1]) % mod
    caseSum[i] = (caseSum[i - 1] + case[i]) % mod

print(case[n])