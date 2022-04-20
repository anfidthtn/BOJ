import sys
n = int(input())
nums = map(int, sys.stdin.readline().split())
minnum = 100000000
maxnum = -100000000
for i in nums:
    if minnum > i:
        minnum = i
    if maxnum < i:
        maxnum = i
print(minnum, maxnum)