maxnum = -1000000000
idx = 0
for i in range(9):
    temp = int(input())
    if maxnum < temp:
        maxnum = temp
        idx = i + 1
print(maxnum)
print(idx)