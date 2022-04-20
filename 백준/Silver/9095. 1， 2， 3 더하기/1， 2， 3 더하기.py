import sys
t = int(input())
case = [1] + [0] * 11
for i in range(1, 12):
    for j in range(1, 4):
        if i - j < 0:
            break
        case[i] += case[i - j]

for _ in range(t):
    print(case[int(sys.stdin.readline())])