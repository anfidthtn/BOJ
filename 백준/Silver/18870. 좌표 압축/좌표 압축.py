n = int(input())
Xn = list(map(int, input().split()))
XnSort = list(set(Xn))
XnSort.sort()
XnDict = {}
for i in range(len(XnSort)):
    XnDict[XnSort[i]] = i
for num in Xn:
    print(XnDict[num], end=' ')