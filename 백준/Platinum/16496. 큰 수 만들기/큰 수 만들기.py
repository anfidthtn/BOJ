from collections import deque
n = int(input())

originNums = [None] + list(input().split())
if originNums.count('0') == len(originNums) - 1:
    print(0)
    exit()

# 2개의 숫자 문자열을 비교해서 우선순위 반환
def compareString(str1, str2):
    idx = 0
    # 자리의 값끼리 비교해서 우선순위 반환
    while len(str1) > idx and len(str2) > idx:
        if int(str1[idx]) > int(str2[idx]):
            return -1
        elif int(str1[idx]) < int(str2[idx]):
            return 1
        idx += 1
    # 뒤에 뭐가 더 나오는지 여부에 따라 우선순위 반환
    # 길이가 같으면 그냥 앞에꺼 우선순위 줘버림
    if len(str1) == idx and len(str2) == idx:
        return -1
    # 길이가 다르면 짧은거 첨부터 vs 긴거 안겹치는부분부터 로 다시 비교
    if len(str1) == idx:
        return compareString(str1, str2[idx:])
    if len(str2) == idx:
        return compareString(str1[idx:], str2)
    

def mergeDeque(dq1, dq2):
    temp = deque()
    while len(dq1) > 0 or len(dq2) > 0:
        if len(dq1) == 0 or len(dq2) == 0:
            if len(dq1) == 0:
                temp += dq2
            else:
                temp += dq1
            return temp
        
        if compareString(dq1[0], dq2[0]) < 0:
            temp.append(dq1.popleft())
        else:
            temp.append(dq2.popleft())
    return temp

# 걍 mergeDeque코드 내가 해놓은거 있길래 그거 쓸라고 mergeSort사용
def mergeStringSort(strList, left, right):
    if left == right:
        return deque([strList[left]])

    dq1 = mergeStringSort(strList, left, (left + right) >> 1)
    dq2 = mergeStringSort(strList, ((left + right) >> 1) + 1, right)
    
    return mergeDeque(dq1, dq2)

nums = mergeStringSort(originNums, 1, n)
for num in nums:
    print(num, end='')