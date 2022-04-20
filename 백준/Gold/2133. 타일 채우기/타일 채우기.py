import sys
n = int(input())
if n % 2 == 1:
    print(0)
    exit()
n //= 2

case = [None, 3, 11]
for i in range(3, n + 1):
    count = 2
    for j in range(1, i - 1):
        count += 2 * case[j]
    count += case[1] * case[i - 1]
    case.append(count)
print(case[n])