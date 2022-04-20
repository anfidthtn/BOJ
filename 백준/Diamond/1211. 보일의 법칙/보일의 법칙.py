import sys
import math

maxDigit = 18

# 각 숫자별 수의 소인수다. 0은 수학적으로 (0, 0, 0, 0)이 아니지만, 뒤에 프로그래밍의 편의를 위해서 그렇게 넣어서 정의한다.
expoDict = {
    0 : (0, 0, 0, 0), 
    1 : (0, 0, 0, 0), 
    2 : (1, 0, 0, 0), 
    3 : (0, 1, 0, 0), 
    4 : (2, 0, 0, 0), 
    5 : (0, 0, 1, 0), 
    6 : (1, 1, 0, 0), 
    7 : (0, 0, 0, 1), 
    8 : (3, 0, 0, 0), 
    9 : (0, 2, 0, 0)
}

'''
한 자리 수 a에 대해 a는 a와 같음
c < 10, b - 1 < b 이므로
c * (b - 1) < 10 * b
c * b < 10 * b + c
두 자리수 bc에 대해 bc > b * c
n자리수도 확장가능
=> dp(num) < num

num * digit-product(num) = self-product(num)
dp(num) < num
dp(num) * dp(num) < num * dp(num) = sp(num) <= 10 ^ maxDigit
dp(num) < 10 ^ (maxDigit // 2)
'''

'''
pMax : 자리수의 곱으로 이룰 수 있는 최대 dp(num)값까지 각 소인수가 최대 몇 개 들어갈 수 있는지 (+1해서 0개도 세어줌)
'''
pMax = [1, 1, 1, 1]
p = [2, 3, 5, 7]
for i in range(4):
    while p[i] ** pMax[i] < 10 ** ((maxDigit + 1) // 2):
        pMax[i] += 1
# print(pMax)

'''
saved : 기존에 나온 경우를 저장해둔 곳.
음수는 저장되지 않은 상태이며,
0개 이상으로 구해지면 해당 위치에 저장한다.
예를 들어
saved[5][2][1][1][1] == 3 이면
6(5 + 1)자리를 채우고, 자리의 곱이 2^2 * 3^1 * 5^1 * 7^1 인 경우가 3가지라는 말이다.
'''
saved = [
    [
        [
            [
                [
                    -1 for _ in range(pMax[3])
                ]
                for _ in range(pMax[2])
            ]
            for _ in range(pMax[1])
        ]
        for _ in range(pMax[0])
    ]
    for _ in range(maxDigit)
]


# 각 소수(2357)별로 더 넣을 수 있는 개수 (뒤에서 [0, 0, 0, 0] [1, 2, 0, 1] 이런식으로 다른 리스트의 주소값을 저장할 예정이라 빈 리스트로 둔다.)
primeRemain = []

'''
getCount : 앞에 n자리가 채워진 상태에서 뒤에 경우의 수가 얼마나 오는지 확인하는 함수
예를 들어 5자리의 경우의 수를 봐야한다면
0자리가 채워진 상태에서
0.... 상태로 4자리 경우의 수
1.... 상태로 4자리 경우의 수
2.... 상태로 4자리 경우의 수
...
9.... 상태로 4자리 경우의 수
이렇게 각 경우를 구해서 더할 수 있는데,

이걸 재귀적으로 수행하며, 겹치는 부분은 저장해둔 대로 불러와서 더해준다.
예를들어 재귀적으로 여러 경우를 구하는 중에
32... 의 경우의 수를 이미 구한 상태에서 뒤에
61... 의 경우의 수를 구해야한다면
두 경우 다 소인수 2와 3을 하나씩 쓴 상태에 2칸 채운 것으로 동일하므로
[2자리 채움][2하나 씀][3하나 씀] 에 32...을 구하고나서 저장해뒀던 결과를
61...을 계산할 때 가져다 쓸 수 있다.
한다.
'''
def getCount(digit, rangeStart, rangeSize, low, high):
    '''
    22000 ~ 22999까지 1천 개를 탐색한다고 가정하면
    rangeStart 인자에 22000, rangeSize에 1000이 들어오게 된다.
    이 때, 22999에 해당하는 rangeEnd는 rangeStart + rangeSize - 1이 된다.
    '''
    rangeEnd = rangeStart + rangeSize - 1

    if high < low:
        return 0
    if high < rangeStart or rangeEnd < low:
        # 범위가 맞지 않는 경우 0이다.
        return 0
    
    if digit == 18:
        '''
        18자리가 다 찼을 때는 남은 소인수 개수가 정확히 0인지를 확인한다.
        왜 확인해야하냐면, 소인수 개수가 음수가 되는 경우에만 넣지 않았기 때문에
        마지막 숫자를 넣을 때 소인수 개수가 남을 수 있기 때문이다.

        정확히 0이라면 값이 범위 안에 있다는 것이다.
        '''
        for i in range(4):
            if primeRemain[i] != 0:
                # 하나라도 0이 아니면 0
                return 0
        # 전부 0이면 1
        return 1

    if low <= rangeStart and rangeEnd <= high:
        # 범위 내에 해당 구간이 전부 포함되는 구간이면, 이미 구해져 있을 수도 있다.
        if saved[digit][primeRemain[0]][primeRemain[1]][primeRemain[2]][primeRemain[3]] >= 0:
            # 해당 구간이 구해진 구간이라면 (아니면 -1이 있을것이기 때문)
            # 이미 구해둔 값을 반환한다.
            return saved[digit][primeRemain[0]][primeRemain[1]][primeRemain[2]][primeRemain[3]]
    
    count = 0

    # 0 ~ 9를 하나 더 넣으면 탐색 범위가 1/10으로 줄어든다.
    rangeSize //= 10
    for nextDigit in range(10):
        '''
        해당 자리에 0 ~ 9를 넣는다.
        즉, .....의 경우에
        00000 ~ 09999
        10000 ~ 19999
        ... 이런식으로 넣어서 아래를 0000 ~ 9999까지 탐색시키는 것이다.

        '''
        # 이 때 0은 leadingZero 처리용이라서, 앞에 다른 숫자가 있다면 0은 패스해도 된다.
        if rangeStart > 0 and nextDigit == 0:
            # leadingZero를 처리하기 위해 0을 넣었지만, 앞에 다른 수가 있어서 구하는 범위가 0 초과인 경우엔 패스한다.
            continue
        isInsertable = True
        for i in range(4):
            if expoDict[nextDigit][i] > primeRemain[i]:
                # 넣을 수 있는 개수보다, 해당 수(nextDigit)를 넣을 때 필요한 소인수가 적으면 넣을 수 없는 수다.
                # 만약 java처럼 outerLoop가 있었다면 nextDigit 0~9돌리는 저기 continue 달 수 있는데.. ㄲㅂ
                # 없어서 isInsertable 변수 사용
                isInsertable = False
                break
        if isInsertable is False:
            # 넣을 수 없는 수는 넘기기
            continue
        
        '''
        nextDigit을 이번 자리에 넣는 과정이다.
        먼저, nextDigit에 필요한 소인수 개수만큼 remain에서 빼준다.
        그 다음 재귀적으로 아랫자리를 탐색시킨다.
        재귀 이후에는 빼준 소인수 개수를 다시 복원시킨다.
        예를 들어
        5자리 .....에서 2를 넣을 때는
        2의 소인수에 해당하는 primeRemain[0] -= 1을 해주고
        00000 ~ 99999 였던 탐색 범위를
        20000 ~ 29999 로 좁혀서 재귀를 넣어줘야하니
        getCount(digit + 1, rangeStart + nextDigit * rangeSize, rangeSize, low, high)
        이렇게 넣어서
        1자리 채운 상태로, 20000부터, 29999범위까지, 최저값 범위는 low이고, 최대값 범위는 high인 경우의 수를 구해달라!
        라고 하면 된다.
        '''

        for i in range(4):
            # 남은 소인수 개수에서 필요한 소인수 개수 빼기
            primeRemain[i] -= expoDict[nextDigit][i]
        # 위에서 설명한대로 재귀 돌려서 결과 합하기
        count += getCount(digit + 1, rangeStart + nextDigit * rangeSize, rangeSize, low, high)
        for i in range(4):
            # 남은 소인수 개수 복구하기
            primeRemain[i] += expoDict[nextDigit][i]
    
    if low <= rangeStart and rangeEnd <= high:
        # 구간이 범위에 전부 포함 된 경우는 경우의 수를 저장한다.
        saved[digit][primeRemain[0]][primeRemain[1]][primeRemain[2]][primeRemain[3]] = count
    
    return count


totalCount = 0
a, b = map(int, input().split())

for p2 in range(pMax[0] + 1):
    if 2 ** p2 > 10 ** (maxDigit // 2):
        break
    for p3 in range(pMax[1] + 1):
        if 2 ** p2 * 3 ** p3 > 10 ** (maxDigit // 2):
            break
        for p5 in range(pMax[2] + 1):
            if 2 ** p2 * 3 ** p3 * 5 ** p5 > 10 ** (maxDigit // 2):
                break
            for p7 in range(pMax[3] + 1):
                if 2 ** p2 * 3 ** p3 * 5 ** p5 * 7 ** p7 > 10 ** (maxDigit // 2):
                    break
                dpNum = 2 ** p2 * 3 ** p3 * 5 ** p5 * 7 ** p7
                # 범위 내의 모든 소인수 개수 쌍에 대해서 시행
                primeRemain = [p2, p3, p5, p7]
                if dpNum == 192:
                    dpNUM = 192
                totalCount += getCount(0, 0, 10 ** maxDigit, math.ceil(a / dpNum), b // dpNum)

print(totalCount)