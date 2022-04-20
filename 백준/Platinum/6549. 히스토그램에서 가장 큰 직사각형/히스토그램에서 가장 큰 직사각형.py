import sys

while True:
    ti = list(map(int, sys.stdin.readline().split()))
    n = len(ti)
    if n == 1:
        exit()

    maxEarn = 0
    info = [[ti[1], 1]]

    for i in range(2, n):
        if len(info) > 0 and ti[i] < info[-1][0]:
            while len(info) > 1 and ti[i] <= info[-2][0]:
                nowInfo = info.pop()
                earn = nowInfo[0] * (i - nowInfo[1])
                maxEarn = max(maxEarn, earn)
            if ti[i] < info[-1][0]:
                earn = info[-1][0] * (i - info[-1][1])
                maxEarn = max(maxEarn, earn)
                info[-1][0] = ti[i]
        else:
            info.append([ti[i], i])
    while len(info) > 0:
        nowInfo = info.pop()
        earn = nowInfo[0] * (n - nowInfo[1])
        maxEarn = max(maxEarn, earn)
    print(maxEarn)
