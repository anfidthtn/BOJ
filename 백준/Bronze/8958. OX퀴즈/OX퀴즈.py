import sys
T = int(input())
for _ in range(T):
    temp = sys.stdin.readline().rstrip().split('X')
    tempsum = 0
    for straight in temp:
        tempsum += len(straight) * (len(straight) + 1) // 2
    print(tempsum)