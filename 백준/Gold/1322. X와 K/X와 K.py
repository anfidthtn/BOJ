x, k = map(int, input().split())

xBin = list(format(x, 'b'))
target = list(format(k, 'b'))
xBin.reverse()
target.reverse()
resultBin = ['0'] * (len(xBin) + len(target))

insertIdx = 0
for targetIdx in range(len(target)):
    while len(xBin) > insertIdx and xBin[insertIdx] == '1':
        insertIdx += 1
    resultBin[insertIdx] = target[targetIdx]
    insertIdx += 1
resultBin.reverse()
resultBinStr = "".join(resultBin)
print(int(resultBinStr, 2))