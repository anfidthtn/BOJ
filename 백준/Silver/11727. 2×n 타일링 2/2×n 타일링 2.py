n = int(input())

case = [0, 1, 3]
for i in range(3, n + 1):
    case.append((case[i - 1] + case[i - 2] * 2) % 10007)
print(case[n])