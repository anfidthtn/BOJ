import sys

x1, y1, x2, y2 = map(int, sys.stdin.readline().split())

count = [0] * 6

Xsections = []
Ysections = []
if x2 <= (x1 // 5) * 5 + 5:
    Xsections.append((x1, x2))
else:
    Xsections.append((x1, (x1 // 5) * 5 + 5))
    Xsections.append(((x1 // 5) * 5 + 5, (x2 // 5) * 5))
    Xsections.append(((x2 // 5) * 5, x2))
if y2 <= (y1 // 5) * 5 + 5:
    Ysections.append((y1, y2))
else:
    Ysections.append((y1, (y1 // 5) * 5 + 5))
    Ysections.append(((y1 // 5) * 5 + 5, (y2 // 5) * 5))
    Ysections.append(((y2 // 5) * 5, y2))

def getShape(cord):
    x = cord[0] // 5
    y = cord[1] // 5
    if (x + y) % 2 == 0:
        return 1 # Hor
    else:
        return 0 # Ver


for Xsection in Xsections:
    for Ysection in Ysections:
        if (Xsection[1] - Xsection[0]) % 5 == 0 and (Ysection[1] - Ysection[0]) % 5 == 0:
            count[5] += ((Xsection[1] - Xsection[0]) * (Ysection[1] - Ysection[0])) // 5
            continue # 뒤를 else처리하는거보다 깔끔할 듯
            
        shape = getShape((Xsection[0], Ysection[0]))

        if (Xsection[1] - Xsection[0]) % 5 == 0:
            if shape == 1:
                HorSecCount = ((Xsection[1] - Xsection[0]) // 5 + 1) // 2
                VerSecCount = ((Xsection[1] - Xsection[0]) // 5) // 2
            else:
                HorSecCount = ((Xsection[1] - Xsection[0]) // 5) // 2
                VerSecCount = ((Xsection[1] - Xsection[0]) // 5 + 1) // 2
            
            count[Ysection[1] - Ysection[0]] += VerSecCount * 5
            count[5] += HorSecCount * (Ysection[1] - Ysection[0])
            continue
        
        if (Ysection[1] - Ysection[0]) % 5 == 0:
            if shape == 1:
                HorSecCount = ((Ysection[1] - Ysection[0]) // 5 + 1) // 2
                VerSecCount = ((Ysection[1] - Ysection[0]) // 5) // 2
            else:
                HorSecCount = ((Ysection[1] - Ysection[0]) // 5) // 2
                VerSecCount = ((Ysection[1] - Ysection[0]) // 5 + 1) // 2
            
            count[Xsection[1] - Xsection[0]] += HorSecCount * 5
            count[5] += VerSecCount * (Xsection[1] - Xsection[0])
            continue

        # 앞의 if, continue처리로 여기는 if (Xsection[1] - Xsection[0]) % 5 != 0 and (Ysection[1] - Ysection[0]) % 5 != 0:와 같은 위치
        if shape == 1:
            count[Xsection[1] - Xsection[0]] += Ysection[1] - Ysection[0]
        else:
            count[Ysection[1] - Ysection[0]] += Xsection[1] - Xsection[0]

finalCount = count[5]
if count[4] > 0:
    temp = min(count[1], count[4])
    count[4] -= temp
    count[1] -= temp
    finalCount += temp
    if count[4] > 0:
        finalCount += count[4]
if count[3] > 0:
    temp = min(count[2], count[3])
    count[3] -= temp
    count[2] -= temp
    finalCount += temp
    if count[3] > 0:
        temp = min(count[1] // 2, count[3])
        count[1] -= temp * 2
        count[3] -= temp
        finalCount += temp
        if count[3] > 0:
            if count[1] > 0:
                count[1] = 0
            finalCount += count[3]
if count[2] > 0:
    temp = min(count[1], count[2] // 2)
    count[2] -= temp * 2
    count[1] -= temp
    finalCount += temp
    if count[2] > 0:
        temp = min(count[1] // 3, count[2])
        count[1] -= temp * 3
        count[2] -= temp
        finalCount += temp
        if count[2] > 0:
            if count[1] > 0:
                count[1] = 0
            finalCount += (count[2] + 1) // 2
finalCount += (count[1] + 4) // 5
print(finalCount)
