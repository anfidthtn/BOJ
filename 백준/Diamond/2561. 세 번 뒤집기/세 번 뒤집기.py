n = int(input())
nums = [0] + list(map(int, input().split())) + [n + 1]
# 0과 n + 1을 추가해서 뒤에서 역순정렬된 것을 올바른 최종상태로 판정하는 현상을 없애고
# 기타 다른 연산도 편하게 할 수 있게 한다.

NO = n + 100
START = 0
END = 1

sections = [{'nums' : [0, 0], 'points' : [0, NO]}]

def sectionClose(sections, endIdx):
    sections[-1]['nums'] = tuple(sections[-1]['nums']) # 나중에 복사할 때 주소복사 아닌 값 복사 해야해서 이렇게
    sections[-1]['points'][END] = endIdx # 구간 끝 지점을 현재 탐색지점 전으로 만듦
    sections[-1]['points'] = tuple(sections[-1]['points']) # 마찬가지

def sectionOpen(sections, startIdx):
    sections.append({'nums' : [nums[startIdx], nums[startIdx]], 'points' : [startIdx, NO]})

def sectionAddComponent(sections, addIdx):
    sections[-1]['nums'][END] = nums[addIdx]

def sectionNumReverse(section):
    new_nums = (section['nums'][END], section['nums'][START])
    section['nums'] = new_nums

def mergeSection(targetSection, theOtherSection):
    merge_nums = (targetSection['nums'][START], theOtherSection['nums'][END])
    merge_points = (targetSection['points'][START], theOtherSection['points'][END])
    targetSection['nums'] = merge_nums
    targetSection['points'] = merge_points

def setSectionPoint(prevSection, nowSection):
    now_diff = abs(nowSection['nums'][START] - nowSection['nums'][END])
    nowSection['points'] = (prevSection['points'][END] + 1, prevSection['points'][END] + 1 + now_diff)

def checkSectionMergeAble(targetSection, theOtherSection):
    # 두 구간이 합쳐질 수 있는지 파악한 후 합쳐질 수 있으면 targetSection의 내부 정보를 바꾸는 식으로 확인한다.
    if targetSection['points'][END] + 1 == theOtherSection['points'][START]: # 앞 구간 끝지점, 뒷구간 시작점 맞는 경우 (사실 여기 들어온다면 무조건인데 혹시 몰라서 체크)
        sectionDiff = targetSection['nums'][END] - theOtherSection['nums'][START] # 앞뒤 섹션간 숫자 차이
        if targetSection['nums'][START] == targetSection['nums'][END] or theOtherSection['points'][START] == theOtherSection['points'][END]:
            # 앞 구간이나 뒷 구간 둘 중 하나가 단일숫자로 이루어진 구간일 때
            if abs(sectionDiff) == 1:
                # 1차이나는 숫자라면 오름차순 내림차순 여부 볼 필요없이 합침
                mergeSection(targetSection, theOtherSection)
                return True
            else:
                # 2차이 이상이 나버리면 합치지 못함
                return False
        else: # 둘 다 단일구간이 아닐 때. (elif 처리하다가 실수할까봐 ㅎㅎ)
            if abs(sectionDiff) > 1:
                # 2 차이 이상 나면 합칠 수 없음
                return False
            # 둘의 차이가 1이거나 -1인 경우
            targetDiff = targetSection['points'][START] - targetSection['points'][END]
            theOtherDiff = theOtherSection['points'][START] - theOtherSection['points'][END]
            if targetDiff * theOtherDiff > 0: # 둘이 부호가 같은 경우 (내림차순이든 오름차순이든 같은 경우)
                mergeSection(targetSection, theOtherSection) # 합칠 수 있음
                return True
            else: # 둘이 부호가 다르면 하나는 내림차순, 하나는 오름차순이니 합칠 수 없음
                return False
    else:
        # 여기는 안 오긴 하는데 혹시 몰라서 체크
        sections[1002103] = 112321312 
        return False

def getLenSection(section):
    return section['points'][END] - section['points'][START] + 1

# 현재 구간과, 현재 구간에서 뒤집을 구간을 주면 다음 구간에 대한 [sections, count, history] 반환
def getNextInfos(nowInfo, rStartSectionIdx, rEndSectionIdx):
    nextInfoList = []

    nowSections = nowInfo[0]

    # 구간 단위로 리버스 하는 경우
    tempSections = []
    tempSections.append(nowSections[rStartSectionIdx - 1]) # 얘는 뒤집을 거 아니니 얕은복사해도 됨(값을 보기만 할 예정)
    for reverseTempIdx in range(rEndSectionIdx, rStartSectionIdx - 1, -1): # j번째부터 i번째까지 내려가면서 뒤집어넣음
        reversedTempSection = nowSections[reverseTempIdx].copy() # 얘는 뒤집어야해서 깊은 복사
        sectionNumReverse(reversedTempSection) # 색션의 시작넘버, 끝 넘버를 뒤집음
        tempSections.append(reversedTempSection)
    tempSections.append(nowSections[rEndSectionIdx + 1]) # 얘는 뒤집을 거 아니니 얕은복사해도 됨(값을 보기만 할 예정)

    # 지금 tempSections에는 넘버만 뒤집힌 상태로 애들이 있음
    newSections = [] # tempSection에서 START, END point를 재설정하고, 그에 따른 합치기 등을 함
    for leftNonReverseIdx in range(0, rStartSectionIdx): # i ~ j번째까지 회전시켰으니 좌측 0 ~ (i - 1)번째까지는 그대로
        newSections.append(nowSections[leftNonReverseIdx].copy()) # (당장 여기서 뿐만 아니라 뒤에도)값이 바뀔 수 있으니 깊은 복사 해줘야함
    for reverseIdx in range(1, rEndSectionIdx - rStartSectionIdx + 3): # 1 ~ 뒤집은 구간 개수 만큼 봐야함
        # 원본의 인덱스 4 ~ 6 를 회전했다 치면
        # tempSections의
        # 인덱스 0에 원본 색션 인덱스 3이 들어있고
        # 인덱스 1 ~ 3에 회전한 색션 6 ~ 4이 들어있고
        # 인덱스 4에 원본 색션 인덱스 7이 들어있음
        # 이 for문에서는 원본의 6 ~ 4(temp의 인덱스 1 ~ 3), 7(temp의 인덱스 4)에 대해 START, END 포인트를 맞춰주면서 합칠 수 있는지 여부를 확인할 것임
        reversedSection = tempSections[reverseIdx]
        setSectionPoint(newSections[-1], reversedSection)
        if checkSectionMergeAble(newSections[-1], reversedSection) is False:
            newSections.append(reversedSection)
    for rightNonReverseIdx in range(rEndSectionIdx + 2, len(nowSections)): # 나머지(위의 예시에서 원본의 인덱스 8 ~ 9)는 그대로 뒤에 붙여줌 
        newSections.append(nowSections[rightNonReverseIdx].copy()) # 깊은 복사
    
    # 위의 처리(돌리기) 이후
    # 더 돌릴 횟수가 0번 남으면(nowInfo[1] == 2상태) 1구간(완전히 오름차순 정렬 완료)만 있어야하고
    # 더 돌릴 횟수가 1번 남으면(nowInfo[1] == 1상태) 최대 3구간 (가운데 구간을 1번 돌려서 0번남기며 1구간 남기기로 전환)까지여야하고
    # 더 돌릴 횟수가 2번 남으면(nowInfo[1] == 0상태) 최대 5구간 (1번 남은 상황의 3구간에서 구간 사이가 아닌 구간 내부의 시작점, 구간 내부의 끝점 기준으로 돌리면 5구간까지 나온다.)까지여야 함
    # 더 돌릴 횟수에 따라 기준 구간수보다 더 많은 구간이 나눠진 상태라면 더이상 돌려도 남은 횟수 안에 복구가 불가능해서 early stop
    if len(newSections) <= 5 - (nowInfo[1] * 2):
        nextHistory = nowInfo[2].copy()
        # 현재 색션에서 Start의 시작점 - End의 끝점을 돌렸으므로, 해당 지점을 history에 넣어준다.
        nextHistory.append((nowSections[rStartSectionIdx]['points'][START], nowSections[rEndSectionIdx]['points'][END]))
        nextInfoList.append([newSections, nowInfo[1] + 1, nextHistory])
    
    # 6712345 -> 6543217 -> 1234567
    # 3456712 -> 1765432 -> 1234567
    # 같은 상황 처리용이고
    # 678 12345 같은 3자리 이상 쌍은 -> 65432187 -> 12345687 -> 12345678 로 횟수가 그대로이기 때문에 할 필요 없고
    # 67 98 12345 같은 경우는 3번만에 되지도 않음
    # 6 87 12345 같은 상황은 애초에 6 54321 78 -> 12345 6 78 으로 이 위에서 처리가 된다.
    if rEndSectionIdx - rStartSectionIdx == 1 and abs(nowSections[rStartSectionIdx]['nums'][START] - nowSections[rEndSectionIdx]['nums'][END]) == 1:
        if getLenSection(nowSections[rStartSectionIdx]) >= 2 and getLenSection(nowSections[rEndSectionIdx]) >= 2:
            tempSectionsInfoList = []

            for splitIdx in [rStartSectionIdx, rEndSectionIdx]:
                if splitIdx == rStartSectionIdx:
                    reverseIdx = rEndSectionIdx
                else:
                    reverseIdx = rStartSectionIdx
                if getLenSection(nowSections[splitIdx]) == 2:
                    tempSections = []
                    tempSections.append(nowSections[rStartSectionIdx - 1].copy())
                    splitSection = {'nums' : (nowSections[splitIdx]['nums'][START], nowSections[splitIdx]['nums'][START]), 'points' : (NO, NO)}
                    setSectionPoint(tempSections[-1], splitSection)
                    tempSections.append(splitSection)
                    reversedSection = nowSections[reverseIdx].copy()
                    sectionNumReverse(reversedSection)
                    setSectionPoint(tempSections[-1], reversedSection)
                    tempSections.append(reversedSection)
                    splitSection = {'nums' : (nowSections[splitIdx]['nums'][END], nowSections[splitIdx]['nums'][END]), 'points' : (NO, NO)}
                    setSectionPoint(tempSections[-1], splitSection)
                    tempSections.append(splitSection)
                    tempSectionsInfoList.append([tempSections, (min(nowSections[splitIdx]['points'][END], nowSections[reverseIdx]['points'][START]), max(nowSections[splitIdx]['points'][START], nowSections[reverseIdx]['points'][END]))])
            
            for tempSections in tempSectionsInfoList:
                tempHistory = tempSections[1]
                tempSections = tempSections[0]
                # 지금 tempSections에는 넘버만 뒤집힌 상태로 애들이 있음
                newSections = [] # tempSection에서 START, END point를 재설정하고, 그에 따른 합치기 등을 함
                for leftNonReverseIdx in range(0, rStartSectionIdx): # i ~ j번째까지 회전시켰으니 좌측 0 ~ (i - 1)번째까지는 그대로
                    newSections.append(nowSections[leftNonReverseIdx].copy()) # (당장 여기서 뿐만 아니라 뒤에도)값이 바뀔 수 있으니 깊은 복사 해줘야함
                for reverseIdx in range(1, len(tempSections)): # 1 ~ 뒤집은 구간 개수 만큼 봐야함
                    # 원본의 인덱스 4 ~ 6 를 회전했다 치면
                    # tempSections의
                    # 인덱스 0에 원본 색션 인덱스 3이 들어있고
                    # 인덱스 1 ~ 3에 회전한 색션 6 ~ 4이 들어있고
                    # 인덱스 4에 원본 색션 인덱스 7이 들어있음
                    # 이 for문에서는 원본의 6 ~ 4(temp의 인덱스 1 ~ 3), 7(temp의 인덱스 4)에 대해 START, END 포인트를 맞춰주면서 합칠 수 있는지 여부를 확인할 것임
                    reversedSection = tempSections[reverseIdx]
                    setSectionPoint(newSections[-1], reversedSection)
                    if checkSectionMergeAble(newSections[-1], reversedSection) is False:
                        newSections.append(reversedSection)
                for rightNonReverseIdx in range(rEndSectionIdx + 1, len(nowSections)): # 나머지(위의 예시에서 원본의 인덱스 8 ~ 9)는 그대로 뒤에 붙여줌 
                    newSections.append(nowSections[rightNonReverseIdx].copy()) # 깊은 복사
                
                if len(newSections) <= 5 - (nowInfo[1] * 2):
                    nextHistory = nowInfo[2].copy()
                    # 현재 색션에서 Start의 시작점 - End의 끝점을 돌렸으므로, 해당 지점을 history에 넣어준다.
                    nextHistory.append(tempHistory)
                    nextInfoList.append([newSections, nowInfo[1] + 1, nextHistory])
    
    return nextInfoList

for i in range(1, n + 2):
    if sections[-1]['points'][END] == NO: # 이전 구간이 끝나지 않은 경우
        if abs(sections[-1]['nums'][END] - nums[i]) >= 2: # 이전 구간 마지막 수 차이 2 이상이면 이전 구간 끝
            sectionClose(sections, i - 1) # 앞 구간 닫기
            sectionOpen(sections, i) # 새 구간 시작하기
        elif sections[-1]['nums'][START] - sections[-1]['nums'][END] == 0: # 이전 지점이 색션의 첫 지점일 때
            sectionAddComponent(sections, i) # 이 때는 차이가 -1이거나 1인 경우만이므로 그냥 그대로 등록
        elif sections[-1]['nums'][START] - sections[-1]['nums'][END] > 0: # 현재 색션이 내림차순일 때
            if sections[-1]['nums'][END] - nums[i] == 1: # 내림차순으로 들어오면 등록
                sectionAddComponent(sections, i) # 등록
            elif sections[-1]['nums'][END] - nums[i] == -1: # == -1상황 오름차순으로 들어오면 앞 구간 닫고 새로 등록
                sectionClose(sections, i - 1) # 색션 닫고
                sectionOpen(sections, i) # 새 색션 시작
            else:
                # 여기는 들어올 리 없는데 혹시나 들어오면 논리나 코딩에 에러가 있는거임
                sections[1000000] = 1000000000000 # 인덱스 에러내기
        elif sections[-1]['nums'][START] - sections[-1]['nums'][END] < 0 : # 현재 색션이 오름차순일 때
            if sections[-1]['nums'][END] - nums[i] == -1: # 오름차순으로 들어오면 등록
                sectionAddComponent(sections, i) # 등록
            elif sections[-1]['nums'][END] - nums[i] == 1: # == 1상황 내림차순으로 들어오면 앞 구간 닫고 새로 등록
                sectionClose(sections, i - 1) # 색션 닫고
                sectionOpen(sections, i) # 새 색션 시작
            else:
                # 여기는 들어올 리 없는데 혹시나 들어오면 논리나 코딩에 에러가 있는거임
                sections[1000000] = 1000000000000 # 인덱스 에러내기
        else:
            # 여기는 들어올 리 없는데 혹시나 들어오면 논리나 코딩에 에러가 있는거임
            sections[1000000] = 1000000000000 # 인덱스 에러내기

sectionClose(sections, n + 1) # 마지막엔 열린 상태로 끝나므로 구간 닫기

waiting = [[sections, 0, []]]

while len(waiting) > 0:
    nowInfo = waiting.pop()

    # 이름 덜 헷갈리게 하려고 변수 선언
    nowSections = nowInfo[0]
    if len(nowSections) == 1:
        for history in nowInfo[2]:
            print(history[0], history[1], sep=' ')
        for i in range(3 - len(nowInfo[2])):
            print('1 1')
        exit()
    # 색션의 좌우측 끝은 회전구간에 넣을 필요 없음 ((0, 0)과 (n + 1, n + 1)의 삽입으로 가장자리 구간은 정렬된 구간임)
    # 인덱스 i ~ j번째 색션까지 회전할거임
    # (1 ~ 10번까지 10개 색션이 있다고 가정하면 2 ~ 9번까지를 시작점, 시작점 ~ 9번까지를 끝점으로 회전)
    for i in range(1, len(nowSections) - 1): # 위의 가정대로면 2(인덱스 1) ~ 9(인덱스 8)번까지 시작점
        for j in range(i, len(nowSections) - 1): # 시작점 ~ 9(인덱스 8)까지 끝점
            for nextInfo in getNextInfos(nowInfo, i, j):
                waiting.append(nextInfo)