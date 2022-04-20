import sys
n = int(sys.stdin.readline())

maxEarn = 0
info = [[int(sys.stdin.readline()), 1]]

for idx in range(2, n + 1):
    num = int(sys.stdin.readline())
    if len(info) > 0 and num < info[-1][0]:
        while len(info) > 1 and num <= info[-2][0]:
            nowInfo = info.pop()
            earn = nowInfo[0] * (idx - nowInfo[1])
            maxEarn = max(maxEarn, earn)
        if num < info[-1][0]:
            earn = info[-1][0] * (idx - info[-1][1])
            maxEarn = max(maxEarn, earn)
            info[-1][0] = num
    else:
        info.append([num, idx])
while len(info) > 0:
    nowInfo = info.pop()
    earn = nowInfo[0] * (n + 1 - nowInfo[1])
    maxEarn = max(maxEarn, earn)
print(maxEarn)
