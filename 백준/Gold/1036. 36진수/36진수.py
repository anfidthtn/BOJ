'''
https://www.acmicpc.net/problem/1036
36진수

큰 수 연산이 지원되는 파이썬이기에 가능한 풀이로 풀이 시도.
count라는 딕셔너리에 각 숫자(문자)를 자리값 개수만큼 합해서 저장함.

이후에 Z(35)의 경우 변환과 상관없이 최대를 유지하므로, Z에 대해서는 35를 곱해서 최종 합계에 더해준다.
0 ~ Y(34)에 대해서
입력받는 k번만큼 (남은 숫자(문자)가 있다면)
각각 현재 값과 Z로 변환했을 때의 차이인 (35 - 숫자(문자)) * (해당 숫자의 자리값 개수) 가 최대가 되는 숫자(문자)를 찾아서 Z로 변환하고 더한다.

최종적으로 더해진 값을 36진수화 해서 출력한다.
'''
n = int(input())

# 자리값을 저장할 딕셔너리
count = {}

# 입력받아서 자리값 개수를 계산해서 저장
for _ in range(n):
    s = input()
    for i in range(len(s)):
        if s[-1 - i] in count:
            count[s[-1 - i]] += 36 ** i
        else:
            count[s[-1 - i]] = 36 ** i

# 최종 합계 저장 변수
totalSum = 0

# 최대값인 Z에 대해서는 k와 무관하게 35 * 자리값 개수를 해주면 됨
if 'Z' in count:
    totalSum += count.pop('Z') * 35

# k(== int(input()))만큼 반복
for _ in range(int(input())):
    # 남은 숫자(문자)가 없으면 중단
    if len(count) == 0:
        break
    # 남은 숫자(문자) 중 Z로 바꿨을 때 최종 합계 차이의 최대값을 저장할 변수
    maxDiff = 0
    for key in count.keys():
        # symbolDiff : 36진수에서 해당 숫자(문자)가 Z(35)와 얼마나 차이가 나는지
        if 'A' <= key and key <= 'Z':
            symbolDiff = ord('Z') - ord(key)
        else:
            # == '0' <= key and key <= '9':
            symbolDiff = 35 - int(key)

        # Z와 차이 * 해당 개수 = 차이값
        if symbolDiff * count[key] > maxDiff:
            # 차이값이 기존보다 더 크면 최대 차이를 갱신하고 키를 임시저장
            maxKey = key
            maxDiff = symbolDiff * count[key]
    
    # count에 key가 존재할 때 실행되고,
    # symbolDiff > 0, count[key] > 0 이므로 maxKey는 for문 내에서 무조건 계산되므로 초기화 필요하진 않음
    # maxKey(차이가 최대가 된 키)를 Z로 바꿨다고 가정하고 35 * 자리값 개수를 더해줌
    totalSum += 35 * count[maxKey]
    # 해당 키는 Z로 바꿨으니 딕셔너리에서 제외
    count.pop(maxKey)

# 남은 key에 대해서는 해당 key의 고유값대로 계산
for key in count.keys():
    if 'A' <= key and key <= 'Z':
        symbolValue = ord(key) - ord('A') + 10
    else:
        # == '0' <= key and key <= '9':
        symbolValue = int(key)
    totalSum += symbolValue * count[key]

# 36진수로 바꿔서 result에 역순으로 저장됨. 0은 별도처리 (Z1E -> [14(E), 1, 35(Z)])
def tenTo36(num, result):
    if num <= 0:
        return
    result.append(num % 36)
    tenTo36(num // 36, result)

# print(totalSum)
result = []
tenTo36(totalSum, result)
# print(result)

# 0은 별도 처리
if len(result) == 0:
    result = [0]

# 역순 36진수 똑바로 출력
for i in range(len(result) - 1, -1, -1):
    if 10 <= result[i] and result[i] <= 35:
        print(chr(ord('A') + result[i] - 10), end='')
    else:
        print(result[i], end='')