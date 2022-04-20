import sys
# 아 문제 조건 제대로 안 읽어서 중복만 없으면 되는 줄 알고 자리수 안 맞췄는데
# 코드 다시짜기 귀찮으니 대충 주요부분만 수정해야겠다
# 근데 자리수 안 맞춘 테케가 91%에 있을줄은 몰랐네

n, d = map(int, input().split())

def getBasedNum(num10, base):
    result = []
    while num10 > 0:
        result.append(num10 % base)
        num10 //= base
    if len(result) == 0:
        return [0]
    else:
        return result

basedNum = getBasedNum(n, d)

if len(basedNum) > d:
    print(-1)
    exit()

def plusOne(basedNum, base, digit):
    basedNum[digit] += 1
    if basedNum[digit] == base:
        basedNum[digit] = 0
        if len(basedNum) == digit + 1:
            basedNum.append(1)
            if len(basedNum) > base:
                print(-1)
                exit()
        else:
            plusOne(basedNum, base, digit + 1)

plusOne(basedNum, d, 0)

def initUsedNum():
    return [None] + [False] * d

usedNum = initUsedNum()

def plusOneWithInit(basedNum, base, digit):
    basedNum[digit] += 1
    if basedNum[digit] == base:
        basedNum[digit] = 0
        if len(basedNum) == digit + 1:
            basedNum.append(1)
            if len(basedNum) > base:
                print(-1)
                exit()
        else:
            for i in range(digit + 1):
                basedNum[i] = 0
            plusOne(basedNum, base, digit + 1)

if len(basedNum) < d: # 자리수 맞추기 위한 것 ㅠㅠ
    while len(basedNum) < d:
        basedNum.append(0)
    plusOneWithInit(basedNum, d, d - 1)

checkIdx = len(basedNum) - 1
while checkIdx >= 0:
    nowNum = basedNum[checkIdx]
    if usedNum[nowNum] is True:
        for i in range(checkIdx):
            basedNum[i] = 0
        plusOneWithInit(basedNum, d, checkIdx)
        usedNum = initUsedNum()
        checkIdx = len(basedNum) - 1
    else:
        usedNum[nowNum] = True
        checkIdx -= 1

def get10basedNum(basedNum, base):
    result = 0
    for i in range(len(basedNum)):
        result += basedNum[i] * base ** i
    return result
# print(basedNum)
print(get10basedNum(basedNum, d))