import sys
from collections import deque

n, m = map(int, input().split())

isTest = False

'''
미로 탈출 https://www.acmicpc.net/problem/1473
좌우에 문이 있으면 10
상하에 문이 있으면 01
즉
A : 11
B : 00
C : 01
D : 10
이며, 버튼이 눌리면 각 비트가 스위칭 되면서 C와 D만 서로 변한다.
'''
status = []
'''
status : 최초 미로의 상태. n개의 m * 2비트의 정수로 저장
'''
for _ in range(n):
    info = 0
    row = list(input())
    for ch in row:
        info <<= 2
        if ch == 'A':
            info += 3
        elif ch == 'D':
            info += 2
        elif ch == 'C':
            info += 1
    status.append(info)

if isTest is True:
    print(status)


'''
이전 이동에서 이미 실행한 경우일 수 있으므로, status를 hash화 해서 집합에 저장하기 위해 해시화를 하는 함수
'''
def hashStatus(status, row, col):
    # n과 m이 2 <= x <= 7 이므로 인덱스화 한 row, col은 1 <= y <= 6이므로 3비트로 처리 가능하다.
    hashValue = row
    hashValue <<= 3
    hashValue += col
    hashValue <<= 3
    for info in status:
        hashValue <<= m * 2
        hashValue += info
    return hashValue


'''
rowFilters : 한 줄에 대해 상하 정보를 필터링해서 교체하기 위한 것
예를 들어서 x = 0b 100111이라는 2진법 100111의 상하정보를 수정해야한다면
temp = (x & rowF01) << 1 (x & rowF01 == 0b 101 => temp == 0b 1010)
x = (x & rowF10) >> 1 (x & rowF10 == 0b 100010 => x == 0b 10001)
x |= temp (x == 0b 11011)
이렇게 첫 칸을 10 -> 01로, 두 번째 칸을 01 -> 10으로 세 번째 칸을 11 -> 11로 수정할 수 있다.
맥시멈이 7칸이므로 그냥 이 부분은 하드코딩으로 01010101010101010101 도배
'''
rowF01 = 0b1010101010101
rowF10 = 0b10101010101010

'''
colFilter도 row와 비슷하게 하드코딩하지만, col의 경우 필터의 인덱스가 반대 순서인 것을 생각해야함.
예를 들어 m == 3일 때 0 ~ 2번 열까지의 정보를 담았다 치면
0번 열은 m - 1 - 0 번째 filter
1번 열은 m - 1 - 1 번째 filter
2번 열은 m - 1 - 2 번ㅉ filter
이렇게 사용해야 한다.
'''
colF01 = [
    0b1, 
    0b100, 
    0b10000, 
    0b1000000, 
    0b100000000, 
    0b10000000000, 
    0b1000000000000]
colF10 = [
    0b10, 
    0b1000, 
    0b100000, 
    0b10000000, 
    0b1000000000, 
    0b100000000000, 
    0b10000000000000]
colOrigin = [
    0b11111111111100, 
    0b11111111110011, 
    0b11111111001111, 
    0b11111100111111, 
    0b11110011111111, 
    0b11001111111111, 
    0b111111111111]

def reverseStatus(status, row, col):
    newStatus = []
    # 열을 먼저 반전시키며 새로운 status 정보를 획득한다.
    for i in range(n):
        newInfo = (status[i] & colF01[m - 1 - col]) << 1
        newInfo += (status[i] & colF10[m - 1 - col]) >> 1
        newStatus.append((status[i] & colOrigin[m - 1 - col]) | newInfo)
    # 행을 반전시킨 정보를 등록한다.
    temp = (newStatus[row] & rowF01) << 1
    newStatus[row] = (newStatus[row] & rowF10) >> 1
    newStatus[row] |= temp

    return newStatus

if isTest is True:
    for num in status:
        print(bin(num))
    print()
    for num in reverseStatus(status, 0, 1):
        print(bin(num))
    print()


colTarget = [
    0b11,
    0b1100,
    0b110000,
    0b11000000,
    0b1100000000,
    0b110000000000,
    0b11000000000000]

def getSymbol(status, row, col):
    if row < 0 or row >= n or col < 0 or col >= m:
        return 0
    return (status[row] & colTarget[m - 1 - col]) >> ((m - 1 - col) * 2)

def getNextPoints(status, row, col):
    nextPoints = []
    nowSymbol = getSymbol(status, row, col)
    if nowSymbol & 1 == 1:
        # 상하에 문이 있을 때
        if getSymbol(status, row + 1, col) & 1 == 1:
            # 아래쪽에도 상하에 문이 있으면
            # 다음 지점으로 아래쪽을 선택가능하게 함
            nextPoints.append((row + 1, col))
        if getSymbol(status, row - 1, col) & 1 == 1:
            # 위쪽에도 상하에 문이 있으면
            # 다음 지점으로 위쪽을 선택가능하게 함
            nextPoints.append((row - 1, col))
    if nowSymbol & 2 == 2:
        # 좌우에 문이 있을 때
        if getSymbol(status, row, col + 1) & 2 == 2:
            # 오른쪽에도 좌우에 문이 있으면
            # 다음 지점으로 오른쪽을 선택가능하게 함
            nextPoints.append((row, col + 1))
        if getSymbol(status, row, col - 1) & 2 == 2:
            # 왼쪽에도 좌우에 문이 있으면
            # 다음 지점으로 왼쪽을 선택가능하게 함
            nextPoints.append((row, col - 1))
    return nextPoints


isVisited = set([hashStatus(status, 0, 0)])
bfsQueue = deque([[status, 0, 0, 0]])
while len(bfsQueue) > 0:
    nowStatus, spTime, nowRow, nowCol = bfsQueue.popleft()

    nextPoints = getNextPoints(nowStatus, nowRow, nowCol)

    for point in nextPoints:
        # 가능한 방향에 대하여 다음 지점으로 등록하며, 다음 지점이 종착지이면 끝
        if point == (n - 1, m - 1):
            print(spTime + 1)
            exit()
        nextHash = hashStatus(nowStatus, point[0], point[1])
        # 이미 진행한 경우이면 패스
        if nextHash in isVisited:
            continue
        # 진행한 경우가 아니면 정보를 등록하고 큐에 삽입
        isVisited.add(nextHash)
        # 큐에 넣을 때 nowStatus는 참조형이지만 값의 수정이 일어나지 않으므로 copy하지 않고 넣음
        bfsQueue.append([nowStatus, spTime + 1, point[0], point[1]])
    
    # 버튼을 누르는 경우
    buttonStatus = reverseStatus(nowStatus, nowRow, nowCol)
    # 이미 진행한 경우 패스, 아니면 등록하고 큐에 삽입
    nextHash = hashStatus(buttonStatus, nowRow, nowCol)
    if nextHash in isVisited:
        continue
    isVisited.add(nextHash)
    bfsQueue.append([buttonStatus, spTime + 1, nowRow, nowCol])

# 이동이 불가능하면 -1
print(-1)