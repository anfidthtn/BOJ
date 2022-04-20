n = int(input())
maxExpo = {3 : 1, 9 : 2, 27 : 3, 81 : 4, 243 : 5, 729 : 6, 2187 : 7}
maxExpo = maxExpo[n]

for i in range(n):
    for j in range(n):
        rowAttribute = i
        colAttribute = j
        isPrinted = False
        for expo in range(maxExpo - 1, -1, -1):
            if 3 ** expo <= rowAttribute and rowAttribute < 2 * 3 ** expo and 3 ** expo <= colAttribute and colAttribute < 2 * 3 ** expo:
                print(' ', end='')
                isPrinted = True
                break
            else:
                rowAttribute %= 3 ** expo
                colAttribute %= 3 ** expo
        if isPrinted is False:
            print('*', end='')
    print()