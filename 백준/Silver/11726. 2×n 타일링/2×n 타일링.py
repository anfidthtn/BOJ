n = int(input())

case = [0, 1, 2]
for i in range(3, n + 1):
    case.append((case[i - 2] + case[i - 1]) % 10007)
print(case[n])