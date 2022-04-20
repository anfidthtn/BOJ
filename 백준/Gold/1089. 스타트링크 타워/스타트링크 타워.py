N = int(input())

tempStringList = []
for _ in range(5):
    tempStringList.append(input())

digit = []

for i in range(N).__reversed__():
    oneDigit = []
    for j in range(5):
        oneDigit.append(tempStringList[j][i * 4 : i * 4 + 3])
    digit.append(oneDigit)

'''
[0 . 2 3 4 5 6 7 8 9] [0 . 2 3 . 5 6 7 8 9] [0 1 2 3 4 5 6 7 8 9]
[0 . . . 4 5 6 . 8 9] [. . . . . . . . . .] [0 1 2 3 4 . . 7 8 9]
[0 . 2 3 4 5 6 . 8 9] [. . 2 3 4 5 6 . 8 9] [0 1 2 3 4 5 6 7 8 9]
[0 . 2 . . . 6 . 8 .] [. . . . . . . . . .] [0 1 . 3 4 5 6 7 8 9]
[0 . 2 3 . 5 6 . 8 9] [0 . 2 3 . 5 6 . 8 9] [0 1 2 3 4 5 6 7 8 9]

[1] [1 4] []
[1 2 3 7] [0~9] [5 6]
[1 7] [0 1 7] []
[1 3 4 5 7 9] [0~9] [2]
[1 4 7] [1 4 7] []
'''


def removeDigit(pdlist, rmdlist):
    for i in rmdlist:
        if pdlist.count(i) > 0:
            pdlist.remove(i)


meanSum = 0.0
for idx, i in enumerate(digit):
    if i[1][1] == '#' or i[3][1] == '#':
        print(-1)
        exit(0)
    possible_digit_list = list(range(10))
    if i[3][0] == '#':
        removeDigit(possible_digit_list, [1, 3, 4, 5, 7, 9])
    elif i[4][0] == '#' or i[4][1] == '#':
        removeDigit(possible_digit_list, [1, 4, 7])
    elif i[2][0] == '#':
        removeDigit(possible_digit_list, [1, 7])
    elif i[0][0] == '#':
        removeDigit(possible_digit_list, [1])

    if i[0][1] == '#':
        removeDigit(possible_digit_list, [1, 4])

    if i[1][0] == '#':
        removeDigit(possible_digit_list, [1, 2, 3, 7])
    elif i[3][2] == '#':
        removeDigit(possible_digit_list, [2])

    if i[1][2] == '#':
        removeDigit(possible_digit_list, [5, 6])

    if i[2][1] == '#':
        removeDigit(possible_digit_list, [0, 1, 7])

    if len(possible_digit_list) > 0:
        tempMean = 0
        for pd in possible_digit_list:
            tempMean += pd
        tempMean *= (10 ** idx)
        tempMean /= len(possible_digit_list)
        meanSum += tempMean

print(meanSum)