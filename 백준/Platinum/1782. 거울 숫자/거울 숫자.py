'''
https://www.acmicpc.net/problem/1782
1자리 : 0, 1, 8
2자리 : 11, 25, 52, 88 4개
3자리 : 1_1, 2_5, 5_2, 8_8 ; _에는 0, 1, 8이 들어갈 수 있으므로 12개
4자리 : 1__1, 2__5, 5__2, 8__8 ; __ 에는 00, 11, 25, 52, 88이 들어갈 수 있으므로 20개
5자리 : 1___1, 2___5, 5___2, 8___8 ; ___에는 0_0, 1_1, 2_5, 5_2, 8_8 ; _에는 0 1 8

타겟 넘버의 자리수 미만에선
1자리 : 3
2자리 : 4 로 누적합 더해주고
2k + 1번째 자리 : 4(11, 25, 52, 88로 감싸진 경우) * 3(정중앙 1자리 0 1 8) * 5(00, 11, 25, 52, 88로 감싼 경우) ^ (k - 1)
2k번째 자리 : 4(11, 25, 52, 88로 감싸진 경우) * 5(00, 11, 25, 52, 88로 감싼 경우) ^ (k - 1)
이렇게 누적합을 더해준다.
'''
caseNonZero = [1, 3, 4]
for n in range(3, 19):
    if n % 2 == 1:
        caseNonZero.append(4 * 3 * 5 ** (n // 2 - 1))
    else:
        caseNonZero.append(4 * 5 ** (n // 2 - 1))

caseZero = [1, 3, 5]
for n in range(3, 19):
    caseZero.append(caseZero[-2] * 5)

'''
100001
188881

200005
288885

500002
588882

800008
888888
이런 식으로 구간검사
저 구간 바깥에 있으면 (ex : 300000) 구간 단위로 개수를 반환할 수 있다.
구간 안쪽에 있으면 재귀적으로 파악해야한다.
재귀로 파악할 때는 0, 0도 신경써야 한다.
만약 500002 ~ 588882 사이에 걸렸다면 00002 ~ 88882 사이에서 확인한다.
'''
def numListComp(aList, bList): # a, b 중 a가 더 크면 1, b가 더 크면 -1 같으면 0
    if len(aList) < len(bList): # 양의 정수이고, leading zero가 없으니 길이 긴 쪽이 크다.
        return -1
    elif len(aList) > len(bList):
        return 1
    for idx in range(len(aList)):
        if int(aList[idx]) > int(bList[idx]):
            return 1
        elif int(aList[idx]) < int(bList[idx]):
            return -1
    return 0

def makeFilledNumList(boundList, fixDigit, fillNum):
    numList = boundList.copy()
    for i in range(fixDigit, len(numList) - fixDigit):
        numList[i] = fillNum
    return numList

def getCount(boundList, num, slotStart):
    numList = list(str(num))
    '''
    slotCount : 경우의 수가 지정되지 않은 슬롯의 수
    예를 들어 1224451이라는 수는
    처음에 ??????? 7칸으로 시작하여서

    1000001과 1888881 사이에 속하므로
    1?????1으로 5칸에서 탐색하고

    1200051과 1288851 사이에 속하므로
    12???51으로 3칸에서 탐색하고
    
    1218151(???의 앞뒤에 1, 1을 넣고 사이에 8을 채운 수)과 1220551(???의 앞 뒤에 2, 5를 넣고 사이에 0을 채운 수) 사이에 속하므로 
    탐색이 끝난다.
    '''
    slotCount = len(numList) - slotStart * 2
    
    '''
    ? 슬롯 개수에 따라 실행
    0개면 경우의 수가 없으므로 0 반환
    1개면 ?에 0, 1, 8을 넣는 경우의 수를 봐야하고
    2개 이상이면 ? 구간의 양 끝에 (첫 자리는 0,0 제외하고) 0, 0 / 1, 1 / 2, 5 / 5, 2 / 8, 8 을 넣고 사이에 0, 8을 넣어 어느 구간에 속하는지 검사하여 경우의 수를 재귀적으로 더해준다.
    '''
    if slotCount <= 0:
        for idx, n in enumerate(numList):
            if int(boundList[idx]) == int(n):
                return 1
        return 0
    if slotCount == 1:
        # 단일자리로 올 수 있는 경우 0, 1, 8
        count = 0
        for c in (0, 1, 8):
            boundList[len(numList) // 2] = c
            if numListComp(numList, boundList) >= 0:
                count += 1
            else:
                return count
        # 구해서 해당 값을 반환한다.
        return count
    '''
    여기서부터는 if slotCount >= 2: 에 해당한다.
    이 때, 첫 자리(slotStart == 0)이면 맨 앞에 0이 올 수 없으므로 이 부분에 대한 처리를 해줘야한다.
    '''
    case = [(0, 0), (1, 1), (2, 5), (5, 2), (8, 8)]
    if slotStart == 0:
        # 맨 앞에 0이 올 수 없는 경우에 대한 처리
        case.remove((0, 0))
    '''
    lowerBoundCheck : 각 경우의 수(0, 0 얘네)마다 ?슬롯 앞 뒤는 경우의 수로, 중앙은 0으로 채우는 구간비교용 리스트
    upper "" : 중앙 8로 채우는 구간비교용 리스트
    '''
    lowerBoundCheck = makeFilledNumList(boundList, slotStart + 1, 0)
    upperBoundCheck = makeFilledNumList(boundList, slotStart + 1, 8)

    count = 0
    for c in case:
        # lowerBoundCheck의 ? 슬롯의 앞뒤를 case로 채운다.
        lowerBoundCheck[slotStart] = c[0]
        lowerBoundCheck[len(numList) - slotStart - 1] = c[1]
        # numList가 lowerBound보다 작으면 현재 계산된 count를 반환한다.
        if numListComp(numList, lowerBoundCheck) < 0:
            return count
        # upperBoundCheck의 ? 슬롯의 앞뒤를 case로 채운다.
        upperBoundCheck[slotStart] = c[0]
        upperBoundCheck[len(numList) - slotStart - 1] = c[1]
        '''
        numList가 upperBound보다 작거나 같다면 현 시점의 ?슬롯 앞 뒤를 case로 고정하고 재귀적으로 탐색한다.
        '''
        if numListComp(numList, upperBoundCheck) <= 0:
            boundList[slotStart] = c[0]
            boundList[len(numList) - slotStart - 1] = c[1]
            return count + getCount(boundList, num, slotStart + 1)
        '''
        upperBound보다도 클 때 slotCount - 2개의 ?에 대해 leadingZero가 될 때의 경우의 수를 더한다.
        마찬가지로 234455로 예를 들면
        ??????(6개)의 11 조합에서
        1????1에서 100001, 188881을 통과한 것이므로
        ????(4개)에 대해서 0000 ~ 8888에 해당하는 모든 경우가 가능하다는 것이기 때문이다.
        '''
        count += caseZero[slotCount - 2]
    # 이 return은 ??????가 888888 초과일 때 이루어진다.
    return count



def getResult(num):
    # 그냥 두 자리 수에 대해서는 재귀 내에서 로직적 예외처리하는 것보다 하드코딩이 편할 것 같아서 하드코딩
    if num < 0:
        return 0
    if num < 1:
        return 1
    if num < 8:
        return 2
    if num < 11:
        return 3
    if num < 25:
        return 4
    if num < 52:
        return 5
    if num < 88:
        return 6
    if num < 101:
        return 7
    digit = 1
    count = 0
    while num >= 10 ** digit:
        count += caseNonZero[digit]
        digit += 1
    count += getCount(list(str(num)), num, 0)
    return count

# getResult(1001)
# next = -1
# for i in range(0, 10000):
#     before = next
#     next = getResult(i)
#     if before < next:
#         print(i)


a, b = map(int, input().split())
print(getResult(b) - getResult(a - 1))