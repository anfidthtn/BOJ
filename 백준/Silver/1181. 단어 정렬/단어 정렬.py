from collections import deque
import sys

n = int(input())
words = []
for _ in range(n):
    words.append(sys.stdin.readline().rstrip())

# 2개의 숫자 문자열을 비교해서 우선순위 반환
def compareString(str1, str2):
    idx = 0
    # 자리의 값끼리 비교해서 우선순위 반환
    if len(str1) < len(str2):
        return -1
    elif len(str1) > len(str2):
        return 1
    
    for i in range(len(str1)):
        c1 = str1[i]
        c2 = str2[i]
        if c1 < c2:
            return -1
        elif c1 > c2:
            return 1
    return 0
    
def mergeDeque(dq1, dq2):
    temp = deque()
    while len(dq1) > 0 or len(dq2) > 0:
        if len(dq1) == 0 or len(dq2) == 0:
            if len(dq1) == 0:
                temp += dq2
            else:
                temp += dq1
            return temp
        
        comp = compareString(dq1[0], dq2[0])
        if comp < 0:
            temp.append(dq1.popleft())
        elif comp > 0:
            temp.append(dq2.popleft())
        else:
            temp.append(dq1.popleft())
            dq2.popleft()
    return temp

# 걍 mergeDeque코드 내가 해놓은거 있길래 그거 쓸라고 mergeSort사용
def mergeStringSort(strList, left, right):
    if left == right:
        return deque([strList[left]])

    dq1 = mergeStringSort(strList, left, (left + right) >> 1)
    dq2 = mergeStringSort(strList, ((left + right) >> 1) + 1, right)
    
    return mergeDeque(dq1, dq2)

words = mergeStringSort(words, 0, n - 1)
for word in words:
    print(word)