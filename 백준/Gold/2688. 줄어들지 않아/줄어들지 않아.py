import sys
t = int(sys.stdin.readline())
cases = [None, [1, 1, 1, 1, 1, 1, 1, 1, 1, 1]]
for _ in range(t):
    n = int(sys.stdin.readline())
    while n >= len(cases):
        temp = [0] * 10
        for i in range(10):
            for j in range(9, i - 1, -1):
                temp[i] += cases[-1][j]
        cases.append(temp)
    print(sum(cases[n]))