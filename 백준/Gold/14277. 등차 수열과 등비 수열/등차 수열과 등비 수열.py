import math
a, b, c, d, u = map(int, input().split())
if u < a:
    countA = 0
else:
    countA = (u - a) // b + 1

if u < c:
    countB = 0
else:
    if d == 1:
        countB = 1
    else:
        temp = c
        countB = 0
        while temp <= u:
            temp *= d
            countB += 1

if countA > 0 and countB > 0:
    if d == 1:
        if a <= c and (c - a) % b == 0:
            countC = 1
        else:
            countC = 0
    else:
        temp = c
        countC = 0
        while temp <= u:
            if temp < a:
                temp *= d
                continue
            if (temp - a) % b == 0:
                countC += 1
            temp *= d
else:
    countC = 0

print(countA + countB - countC)