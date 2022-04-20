n = int(input())

map = []
for i in range(n + 2):
    if i == 0 or i == n + 1:
        map.append(list('-' * (n + 2)))
    else:
        map.append(list('-' + input() + '-'))

count = 0
if n > 4:
    count += (n - 4) ** 2

for i in range(1, n - 1):
    for [j, k] in [[2, -1], [n - 1, 1]]:
        if map[i + 1][j] != '#':
            continue
        nowNum = int(map[i][j + k])
        if nowNum == 0:
            map[i + 1][j] = '-'
        elif nowNum == 3:
            map[i + 1][j] = '*'
            count += 1
        elif nowNum == 1:
            if map[i][j] != '*' and map[i - 1][j] != '*':
                map[i + 1][j] = '*'
                count += 1
            else:
                map[i + 1][j] = '-'
        else:
            if map[i][j] != '*' or map[i - 1][j] != '*':
                map[i + 1][j] = '*'
                count += 1
            else:
                map[i + 1][j] = '-'

for j in range(1, n - 1):
    for [i, k] in [[2, -1], [n - 1, 1]]:
        if map[i][j + 1] != '#':
            continue
        nowNum = int(map[i + k][j])
        if nowNum == 0:
            map[i][j + 1] = '-'
        elif nowNum == 3:
            map[i][j + 1] = '*'
            count += 1
        elif nowNum == 1:
            if map[i][j] != '*' and map[i][j - 1] != '*':
                map[i][j + 1] = '*'
                count += 1
            else:
                map[i][j + 1] = '-'
        else:
            if map[i][j] != '*' or map[i][j - 1] != '*':
                map[i][j + 1] = '*'
                count += 1
            else:
                map[i][j + 1] = '-'

print(count)