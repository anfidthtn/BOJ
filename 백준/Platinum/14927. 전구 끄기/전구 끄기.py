'''
https://www.acmicpc.net/problem/14927 전구 끄기
'''
isTest = False

n = int(input())

origin = [0] * n
for i in range(n):
    for num in list(map(int, input().split())):
        origin[i] <<= 1
        origin[i] += num

if isTest is True:
    for o in origin:
        print(bin(o))
    print()

nowLine = [
    0b11,
    0b111
]
if n == 2:
    # n이 2일때는 세 칸이 동시에 뒤집어지는 경우가 없다.
    nowLine[1] = 0b11
diffLine = [
    0b1
]
while len(nowLine) < n:
    nowLine.append((nowLine[-1] << 1) % (2 ** n))
while len(diffLine) < n:
    diffLine.append(diffLine[-1] << 1)

if isTest is True:
    for o in nowLine:
        print(bin(o))
    print()
    for o in diffLine:
        print(bin(o))
    print()

maxNum = 18 ** 18 + 1
minCount = maxNum
for firstLineCase in range(2 ** n):
    # 새로운 시작
    copyOrigin = origin.copy()
    count = 0
    # n개에 비트에 대해서 순회
    for col in range(n):
        # 00001, 00010, 00100, 01000, 10000 이런식으로 순회하면서 누르는 경우엔 누름
        if diffLine[col] & firstLineCase > 0:
            count += 1
            copyOrigin[0] ^= nowLine[col]
            copyOrigin[1] ^= diffLine[col]
    for row in range(1, n - 1):
        # 맨 마지막 줄 바로 윗 줄까지
        for col in range(n):
            # 바로 윗줄에 켜져있으면 누른다. (누르면 위아래도 바뀐다.)
            if diffLine[col] & copyOrigin[row - 1] > 0:
                count += 1
                copyOrigin[row - 1] ^= diffLine[col]
                copyOrigin[row] ^= nowLine[col]
                copyOrigin[row + 1] ^= diffLine[col]
    # 맨 마지막 줄은
    for col in range(n):
        # 바로 윗줄에 켜져있으면 누른다. (누르면 위만 바뀌긴 하는데, 처리할 필요가 없다.)
        if diffLine[col] & copyOrigin[n - 2] > 0:
            count += 1
            copyOrigin[n - 1] ^= nowLine[col]

    # 모두 꺼진 상태이면 최소값일 때 저장한다.
    if copyOrigin[n - 1] == 0:
        if minCount > count:
            minCount = count

if minCount == maxNum:
    print(-1)
else:
    print(minCount)