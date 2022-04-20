N = int(input())

minCalc = [[0, 0] for _ in range(N + 1)]

def getMinCalc(n):
    if n == 1:
        return [0, 0]
    if minCalc[n][0] > 0:
        return minCalc[n]
    
    minCalc[n][0] = getMinCalc(n - 1)[0]
    minCalc[n][1] = n - 1
    if n % 2 == 0:
        temp = getMinCalc(int(n / 2))
        if minCalc[n][0] > temp[0]:
            minCalc[n][0] = temp[0]
            minCalc[n][1] = int(n / 2)
    if n % 3 == 0:
        temp = getMinCalc(int(n / 3))
        if minCalc[n][0] > temp[0]:
            minCalc[n][0] = temp[0]
            minCalc[n][1] = int(n / 3)
    
    minCalc[n][0] += 1
    return minCalc[n]

for i in range(2, N + 1):
    getMinCalc(i)

print(minCalc[N][0])
now = N
while now > 0:
    print(now, end=' ')
    now = minCalc[now][1]