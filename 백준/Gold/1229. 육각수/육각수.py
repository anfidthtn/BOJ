import sys
n = int(input())

hs = [1, 6]
i = 2
while hs[-1] <= n:
    hs.append(hs[i - 1] + 4 * i + 1)
    i += 1
hs.pop()

case = [0] + [10] * n

hs2Set = set()
for a in hs:
    for b in hs:
        if a + b <= n:
            hs2Set.add(a + b)

if n > 146858:
    if n in hs:
        print(1)
        exit()
    if n in hs2Set:
        print(2)
        exit()
    print(3)
    exit()

for i in range(1, n + 1):
    if i in hs:
        case[i] = 1
        continue
    if i in hs2Set:
        case[i] = 2
        continue
    for h in hs:
        if i - h < 0:
            break
        if n > 146858:
            case[i] = 3
            break
        else:
            case[i] = min(case[i], case[i - h] + 1)
            if case[i] == 3:
                break

print(case[n])
