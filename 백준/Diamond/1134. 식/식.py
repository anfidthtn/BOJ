A, B, C = input().replace('+', ' ').replace('=', ' ').split()

# [(안 받고 안 시킴), (받고 안 시킴), (안 받고 시킴), (받고 시킴)] 의 인덱스
X_GET__X_MAKE = 0
O_GET__X_MAKE = 1
X_GET__O_MAKE = 2
O_GET__O_MAKE = 3
# (인덱스) % 2 => 0 : X_GET(carry 안 받음), 1 : O_GET(carry 받음)
# int((인덱스) / 2) => 0 : X_MAKE(carry 안 만듦), 1 : O_MAKE(carry 만듦)
NOT_SELECTED = -2


RESULT_IMPOSSIBLE = -1

# 코딩 편하게 하려고 스트링 -> 리스트
A = list(A)
B = list(B)
C = list(C)

if (len(A) > 1 and A[0] == '0') or (len(B) > 1 and B[0] == '0') or (len(C) > 1 and C[0] == '0'):
    # 두 자리수 이상짜리가 첫 자리에 0찍히면 불가능
    print(RESULT_IMPOSSIBLE)
    exit()
if len(A) > len(C) or len(B) > len(C):
    # 더할 숫자가 결과 숫자보다 자릿수 높으면 불가능
    print(RESULT_IMPOSSIBLE)
    exit()
elif len(A) + 1 < len(C) and len(B) + 1 < len(C):
    # 더할 숫자들 자릿수가 결과 자릿수보다 2자리 이상 부족하면 불가능
    print(RESULT_IMPOSSIBLE)
    exit()
elif len(A) <= len(C) - 1 and len(B) <= len(C) - 1 and C[0] != '1' and C[0] != '?':
    # 더할 숫자 자릿수 둘 다가 결과 자릿수보다 작으면 결과 자릿수 첫 자리는 1밖에 올 수 없어서 1이나 ?가 아니면 불가능
    print(RESULT_IMPOSSIBLE)
    exit()

if len(A) <= len(C) - 1 and len(B) <= len(C) - 1:
    # 바로 위의 경우에서 ? 이 나온 경우 1로 변환
    C[0] = '1'

# 연산 편하게 하려고 리버스
# 즉, 
# [ ... , 100의 자리, 10의 자리, 1의 자리]
# 에서
# [ 1의 자리, 10의 자리, 100의 자리, ... ]
# 로 교체
A.reverse()
B.reverse()
C.reverse()

def printResult():
    A.reverse()
    for a in A:
        try:
            print(int(a), end='')
        except:
            print('?', end='')
    print('+', end='')
    A.reverse()
    B.reverse()
    for b in B:
        try:
            print(int(b), end='')
        except:
            print('?', end='')
    print('=', end='')
    B.reverse()
    C.reverse()
    for c in C:
        try:
            print(int(c), end='')
        except:
            print('?', end='')
    C.reverse()

# 해당 인덱스에서 Carry받는 여부, Carry 발생 여부를 만족시킬 때 c가 최댓값인 경우를 만들어줌
def getAvailableSet(idx):
    # [(안 받고 안 시킴), (받고 안 시킴), (안 받고 시킴), (받고 시킴)]
    MaxNumSet = [None, None, None, None]
    # 해당 인덱스의 a를 숫자로 치환(없다면 0)
    try:
        a = int(A[idx])
        isANum = True
    except:
        if len(A) > idx:
            # 해당 인덱스의 a가 ?인 경우
            isANum = False
        else:
            a = 0
            isANum = True
    # 해당 인덱스의 b를 숫자로 치환(없다면 0)
    try:
        b = int(B[idx])
        isBNum = True
    except:
        if len(B) > idx:
            isBNum = False
        else:
            b = 0
            isBNum = True
    # 해당 인덱스의 c를 숫자로 치환
    try:
        c = int(C[idx])
        isCNum = True
    except:
        # 해당 인덱스의 b가 ?인 경우
        isCNum = False
    
    # C에 후보숫자로 뭐가 올 수 있는지 저장
    if isCNum is True:
        # c가 숫자이면 c값 그대로
        CcandidateList = [c]
    else:
        # c가 ?이면 9 ~ 0 (높은 것부터)
        CcandidateList = [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
        if idx > 0 and len(C) == idx + 1:
            # c가 맨 윗자리라서 0 못 오면 0 pop
            CcandidateList.pop()
    
    # A도 후보숫자 저장
    if isANum is True:
        # a가 숫자이면 a값 그대로
        AcandidateList = [a]
    else:
        # a가 ?이면 9 ~ 0 (높은 것부터)
        AcandidateList = [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
        if idx > 0 and len(A) == idx + 1:
            # a가 맨 윗자리라서 0 못 오면 0 pop
            AcandidateList.pop()
    
    # B도 후보숫자 저장
    if isBNum is True:
        # b가 숫자이면 b값 그대로
        BcandidateList = [b]
    else:
        # b가 ?이면 9 ~ 0 (높은 것부터)
        BcandidateList = [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
        if idx > 0 and len(B) == idx + 1:
            # b가 맨 윗자리라서 0 못 오면 0 pop
            BcandidateList.pop()

    # 우선순위가 C, A, B 순이므로 해당 순서대로 for문 순회
    isFindAll = False # 4가지 경우를 다 찾았을 때는 for문 early stop
    for Ccandidate in CcandidateList:
        if isFindAll is True:
            break
        for Acandidate in AcandidateList:
            if isFindAll is True:
                break
            for Bcandidate in BcandidateList:
                # 각 경우에 대해 최초로 등장할 때 우선순위가 가장 높은 경우이므로 저장
                # carry 받지 않고, carry 발생시키지 않는 경우
                if MaxNumSet[X_GET__X_MAKE] is None and  Acandidate + Bcandidate == Ccandidate:
                    MaxNumSet[X_GET__X_MAKE] = (Acandidate, Bcandidate, Ccandidate)
                # carry 받고, carry 발생시키지 않는 경우
                if MaxNumSet[O_GET__X_MAKE] is None and  Acandidate + Bcandidate + 1 == Ccandidate:
                    MaxNumSet[O_GET__X_MAKE] = (Acandidate, Bcandidate, Ccandidate)
                # carry 받지 않고, carry 발생시키는 경우
                if MaxNumSet[X_GET__O_MAKE] is None and  Acandidate + Bcandidate == 10 + Ccandidate:
                    MaxNumSet[X_GET__O_MAKE] = (Acandidate, Bcandidate, Ccandidate)
                # carry 받고, carry 발생시키는 경우
                if MaxNumSet[O_GET__O_MAKE] is None and  Acandidate + Bcandidate + 1 == 10 + Ccandidate:
                    MaxNumSet[O_GET__O_MAKE] = (Acandidate, Bcandidate, Ccandidate)
                if MaxNumSet[X_GET__X_MAKE] is not None and MaxNumSet[O_GET__X_MAKE] is not None and MaxNumSet[X_GET__O_MAKE] is not None and MaxNumSet[O_GET__O_MAKE] is not None:
                    isFindAll = True
                    break
    
    if idx == 0:
        # 가장 아랫자리에서는 Carry를 받을 수 없다.
        MaxNumSet[O_GET__X_MAKE] = None
        MaxNumSet[O_GET__O_MAKE] = None
    else:
        if MaxNumSetList[idx - 1][X_GET__O_MAKE] is None and MaxNumSetList[idx - 1][O_GET__O_MAKE] is None:
            # 아랫자리에서 Carry를 만드는 경우가 없으면 이번 자리에 Carry를 받았을 때의 경우의 수는 불가능하니 없앤다.
            MaxNumSet[O_GET__X_MAKE] = None
            MaxNumSet[O_GET__O_MAKE] = None
        if MaxNumSetList[idx - 1][X_GET__X_MAKE] is None and MaxNumSetList[idx - 1][O_GET__X_MAKE] is None:
            # 아랫자리에서 Carry를 안 만드는 경우가 없으면 이번 자리에 Carry를 받지 않았을 때의 경우의 수는 불가능하니 없앤다.
            MaxNumSet[X_GET__X_MAKE] = None
            MaxNumSet[X_GET__O_MAKE] = None
    if len(C) == idx + 1:
        # 가장 윗자리에서는 Carry를 발생시키면 안 된다.
        MaxNumSet[X_GET__O_MAKE] = None
        MaxNumSet[O_GET__O_MAKE] = None

    if MaxNumSet[X_GET__X_MAKE] is None and MaxNumSet[O_GET__X_MAKE] is None and MaxNumSet[X_GET__O_MAKE] is None and MaxNumSet[O_GET__O_MAKE] is None:
        # 모든 경우가 불가능할 때 불가능을 출력한다.
        print(-1)
        exit()
    return MaxNumSet


def compareCase(idx, caseIdx1, caseIdx2):
    tuple1 = MaxNumSetList[idx][caseIdx1]
    tuple2 = MaxNumSetList[idx][caseIdx2]
    # c의 값 비교
    if tuple1[2] > tuple2[2]:
        return caseIdx1
    elif tuple1[2] < tuple2[2]:
        return caseIdx2
    else:
        # a의 값 비교
        if tuple1[0] > tuple2[0]:
            return caseIdx1
        elif tuple1[0] < tuple2[0]:
            return caseIdx2
    
    # 현재 자리에서 우선순위 같은 경우 (아랫 자리를 봐야함)
    return -1




# 여러 튜플들 중 c->a->b 순으로 내림차순 정렬된 index List를 반환한다.
def getDescCaseIdxList(idx):
    descCaseIdxList = []
    for caseIdx in range(4):
        if MaxNumSetList[idx][caseIdx] is not None:
            # None이 아닌 index들 저장
            descCaseIdxList.append(caseIdx)

    for listIdx1 in range(len(descCaseIdxList) - 1):
        for listIdx2 in range(listIdx1 + 1, len(descCaseIdxList) - 1):
            if compareCase(idx, listIdx1, listIdx2) == listIdx2:
                # 우선순위가 바뀌어있다면 순서 바꿈
                descCaseIdxList[listIdx1], descCaseIdxList[listIdx2] = descCaseIdxList[listIdx2], descCaseIdxList[listIdx1]
    
    return descCaseIdxList


def getNoneCount(idx):
    return MaxNumSetList[idx].count(None)


# 위, 아래 인덱스와의 Carry 발생에서의 관계를 해소한다.
def correlationProcess(idx):
    # (인덱스) % 2 => 0 : X_GET(carry 안 받음), 1 : O_GET(carry 받음)
    # int((인덱스) / 2) => 0 : X_MAKE(carry 안 만듦), 1 : O_MAKE(carry 만듦)

    # 선택한 인덱스가 Carry 받아야하는지 여부, Carry 주는지 여부따라 위, 아래의 경우의 수 제한
    if idx > 0: # 2번째 자리부터는 아래자리에서 Carry주는지 여부 확인
        if MaxNumSetList[idx][X_GET__O_MAKE] is None and MaxNumSetList[idx][X_GET__X_MAKE] is None:
            # carry 받아야 하는 경우 아래자리에서 안 주는 경우를 없앤다.
            MaxNumSetList[idx - 1][X_GET__X_MAKE] = None
            MaxNumSetList[idx - 1][O_GET__X_MAKE] = None
        elif MaxNumSetList[idx][O_GET__O_MAKE] is None and MaxNumSetList[idx][O_GET__X_MAKE] is None:
            # carry 받지 못하는 경우 아래자리에서 주는 경우를 없앤다
            MaxNumSetList[idx - 1][X_GET__O_MAKE] = None
            MaxNumSetList[idx - 1][O_GET__O_MAKE] = None

        if chosenSetList[idx - 1] == NOT_SELECTED and getNoneCount(idx - 1) == 3:
            # 아래 자리가 선택하지 않은 곳이고, 경우의 수가 1개 남았다면 세팅하러 간다.
            chosenSetList[idx - 1] = numSetChoice_CaseOnlyOne(idx - 1)


    if idx < len(C) - 1: # 맨 윗자리 바로 전까진 윗자리에서 Carry 받는지 여부 확인
        if MaxNumSetList[idx][X_GET__O_MAKE] is None and MaxNumSetList[idx][O_GET__O_MAKE] is None:
            # carry를 발생 시키지 못하는데 윗 자리에서 Carry를 받아야 하는 경우를 없앤다.
            MaxNumSetList[idx + 1][O_GET__X_MAKE] = None
            MaxNumSetList[idx + 1][O_GET__O_MAKE] = None
        elif MaxNumSetList[idx][X_GET__X_MAKE] is None and MaxNumSetList[idx][O_GET__X_MAKE] is None:
            # carry를 발생 시키는데 윗 자리에서 Carry를 받지 않아야 하는 경우를 없앤다.
            MaxNumSetList[idx + 1][X_GET__X_MAKE] = None
            MaxNumSetList[idx + 1][X_GET__O_MAKE] = None

        if chosenSetList[idx + 1] == NOT_SELECTED and getNoneCount(idx + 1) == 3:
            # 윗 자리가 선택하지 않은 곳이고, 경우의 수가 1개 남았다면 세팅하러 간다.
            chosenSetList[idx + 1] = numSetChoice_CaseOnlyOne(idx + 1)



# 선택가능한 조합이 1개밖에 없는 경우에 대한 처리
def numSetChoice_CaseOnlyOne(idx):
    if chosenSetList[idx] != NOT_SELECTED:
        # 이미 선택된 인덱스의 경우 종료
        return chosenSetList[idx]
    if getNoneCount(idx) == 3:
        # 자리의 경우의 수가 1개밖에 없는 경우 그 녀석으로 선택
        caseIdx = -1
        for i in range(4):
            if MaxNumSetList[idx][i] is not None:
                caseIdx = i
                break
        
        chosenSetList[idx] = caseIdx

        try:
            A[idx] = int(MaxNumSetList[idx][caseIdx][0])
        except:
            pass
        try:
            B[idx] = int(MaxNumSetList[idx][caseIdx][1])
        except:
            pass
        try:
            C[idx] = int(MaxNumSetList[idx][caseIdx][2])
        except:
            pass

        correlationProcess(idx)

        # 어떤 case를 선택했는지 반환
        return caseIdx
    
    # 선택가능한 녀석이 2개 이상인 것은 다른데에서 처리
    return NOT_SELECTED

    
# 윗 자리부터 내려가면서 남은 경우의 수 중에 c 값이 낮은 것 삭제, 삭제에 따른 위아래 Carry여부도 같이 처리
def reduceCaseC(idx):
    if chosenSetList[idx] != NOT_SELECTED:
        # 이미 선택된 인덱스면 할 필요 없음
        return
    maxC = -1
    for caseIdx in range(4):
        if MaxNumSetList[idx][caseIdx] is not None:
            # None이 아닌 index들을 보면서 최댓값 저장
            if MaxNumSetList[idx][caseIdx][2] > maxC:
                maxC = MaxNumSetList[idx][caseIdx][2]
    for caseIdx in range(4):
        if MaxNumSetList[idx][caseIdx] is not None:
            # None이 아닌 index들을 보면서 최댓값보다 낮은거 삭제
            if MaxNumSetList[idx][caseIdx][2] < maxC:
                MaxNumSetList[idx][caseIdx] = None

    # 삭제로 인해 1개만 남으면 1개만 남은 것에 대해 처리
    numSetChoice_CaseOnlyOne(idx)
    
    # None 발생으로 인한 경우의 수 처리
    correlationProcess(idx)


# 윗 자리부터 내려가면서 남은 경우의 수 중에 a 값이 낮은 것 삭제, 삭제에 따른 위아래 Carry여부도 같이 처리
def reduceCaseA(idx):
    if chosenSetList[idx] != NOT_SELECTED:
        # 이미 선택된 인덱스면 할 필요 없음
        return
    maxA = -1
    for caseIdx in range(4):
        if MaxNumSetList[idx][caseIdx] is not None:
            # None이 아닌 index들을 보면서 최댓값 저장
            if MaxNumSetList[idx][caseIdx][0] > maxA:
                maxA = MaxNumSetList[idx][caseIdx][0]
    for caseIdx in range(4):
        if MaxNumSetList[idx][caseIdx] is not None:
            # None이 아닌 index들을 보면서 최댓값보다 낮은거 삭제
            if MaxNumSetList[idx][caseIdx][0] < maxA:
                MaxNumSetList[idx][caseIdx] = None

    # 삭제로 인해 1개만 남으면 1개만 남은 것에 대해 처리
    numSetChoice_CaseOnlyOne(idx)
    
    # None 발생으로 인한 경우의 수 처리
    correlationProcess(idx)


# [1의자리[(안 받고 안 시킴), (받고 안 시킴), (안 받고 시킴), (받고 시킴)],
# 10의자리[(안 받고 안 시킴), (받고 안 시킴), (안 받고 시킴), (받고 시킴)],
#  ...] 이런 식의 각 자리별 c최대->a최대->b 값 세트 구해서 넣을 리스트
MaxNumSetList = [None] * len(C)
for i in range(len(C)):
    MaxNumSetList[i] = getAvailableSet(i)
    # print(MaxNumSetList[i])

chosenSetList = [NOT_SELECTED] * len(C)
for i in range(len(C) - 1, -1, -1):
    chosenSetList[i] = numSetChoice_CaseOnlyOne(i)
# print(MaxNumSetList)
for i in range(len(C) - 1, -1, -1):
    reduceCaseC(i)
for i in range(len(C) - 1, -1, -1):
    reduceCaseA(i)

# print(A, B, C)
# print(MaxNumSetList)
# print(chosenSetList)
printResult()
print()