# 최초입력 문자열
base_string = input()
# 규칙 문자열 S
temp = input()
# $을 기준으로 구분해서 담을 리스트
rule_string = []
# .split('$')을 하면 리스트에 '$'까지는 제대로 안 담겨서 노가다
tempIdx = 1
for idx, i in enumerate(temp):
    if i == '$':
        if len(temp[tempIdx:idx]) > 0:
            rule_string.append(temp[tempIdx:idx])
        tempIdx = idx + 1
        rule_string.append('$')
    elif idx == len(temp) - 1:
        if len(temp[tempIdx:idx + 1]) > 0:
            rule_string.append(temp[tempIdx:idx + 1])
'''
위의 과정이 끝나면 $$aaa$$$2312b$dd는
['$','$','aaa','$','$','$','2312b','$','dd']로 저장됨
'''

# 최대 반복 횟수
max_epoch = int(input())
# 검색할 시작점, 끝점
min_idx, max_idx = map(int, input().split())

# 어차피 끝점 뒤는 볼 필요 없으니 끝점을 MAXIMUM으로 설정
MAXIMUM = max_idx

'''
ay
$ax
10000000000
10000000000 1000000010
이런식으로 rule에 $는 하나이고 반복 횟수가 많을 때
뒤의 else문의 앍고리즘처럼 처리하면 오래걸려서 따로 처리 
'''
if rule_string.count('$') == 1:
    # 입력 문자열 길이 임시저장
    base_len = len(base_string)
    if base_len < min_idx: # 최초 탐색위치가 입력 문자열보다 뒤라면 반복부분만 보면 됨
        min_idx -= base_len
        max_idx -= base_len
    elif base_len <= max_idx: # 최초 탐색위치가 입력 문자열보다 앞이라면 앞부분 먼저 출력
        print(base_string[min_idx - 1:], end='')
        min_idx -= base_len
        max_idx -= base_len
    else: # 둘 다 입력 문자열 범위라면 그냥 출력하고 끝
        print(base_string[min_idx - 1:max_idx], end='')
        exit(0)

    if max_idx <= 0:
        exit(0)

    rule_len = len(rule_string[1]) # ['$', '~~~'] 에서 '~~~'부분 길이 임시 저장
    if rule_len * max_epoch < min_idx: # rule부분을 반복횟수만큼 해도 최초탐색까지 도달하지 못한다면 탐색 범위만큼 - 출력(하고 끝)
        for _ in range(min_idx, max_idx + 1):
            print('-', end='')
    else: # 최초탐색위치가 범위 안인 경우
        # 최초탐색위치를 rule의 길이에 딱 맞게 땡겨오며 최종탐색 위치도 같이 땡겨옴
        temp = int((min_idx - 1) / rule_len)
        if temp < 0:
            temp = 0
        min_idx -= temp * rule_len
        max_idx -= temp * rule_len

        # 반복부분으로 채워야할만큼 채움
        for _ in range(max_epoch - temp):
            if min_idx > 0 and max_idx > 0:
                # 최초탐색위치가 남아있을 때 그만큼 출력
                '''
                ex) abcde 에서 2 ~ 11이라면
                bcde 출력하는 부분
                (이 경우 뒤에서 -3 ~ 6으로 만들고 다음 루프 들어감)
                '''
                print(rule_string[1][min_idx-1:max_idx], end='')
            elif max_idx > 0:
                # 최종탐색 위치가 남아있을 때 그만큼 출력
                '''
                ex) abcde 에서 -3 ~ 6이라면
                abcde 출력하고 -8 ~ 1,
                다음 루프에 a 출력하고 -13 ~ -4(루프 끝)
                '''
                print(rule_string[1][0:max_idx], end='')
            min_idx -= rule_len
            max_idx -= rule_len
            if max_idx <= 0:
                break

        for _ in range(max_idx):
            '''
            abcde -3 ~ 6이여서
            abcde 출력하고 -8 ~ 1까지 왔는데 규칙 반복 횟수가 모자란 경우
            남은 1만큼 - 출력하고 끝내는 것
            '''
            print('-', end='')
else:
    '''
    ['$', 'ㅁㅇㄴㄹㅇㅁ' , '$', '$'] 처럼 여러 '$'가 있는 경우
    '''
    # 반복을 할 때마다 문자열 길이가 어떻게 되는지 DP 역할 하기 위해 저장
    # 최초 저장은 입력 문자열의 길이
    len_epoch = [len(base_string)]

    # len_epoch를 채워넣는 함수. maximum이상은 굳이 계산할 필요 없으니 1을 같이 반환하면서 return
    def len_endless(input_len):
        # input_len : 바로 이전단계의 길이
        if input_len >= MAXIMUM:
            return input_len, 1
        next_len = 0
        # 이번 단계의 길이

        # 룰을 순서대로 보면서 $이면 이전단계 길이, 아니면 그 룰의 길이만큼 길이 추가
        for rule in rule_string:
            if rule == '$':
                next_len += input_len
            else:
                next_len += len(rule)

            # 추가하다가 맥시멈 넘어가면 바로 이전까지만 하면 되므로 중단
            if next_len >= MAXIMUM:
                return next_len, 1
        # 유의미한 반환이란 의미에서 0과 함께 길이 반환
        return next_len, 0

    
    # len_epoch를 채우기위해 위의 함수를 돌리는 과정
    for _ in range(max_epoch - 1):
        next_len, state = len_endless(len_epoch[-1])
        # 중단조건이 반환된 것이면 중단
        if state == 1:
            break
        # 
        # if next_len - len_epoch[-1] < 2 and len(len_epoch) > 2:
        #     break
        len_epoch.append(next_len)


    def printIndex(idx, level):
        if level == 0:
            if idx > len_epoch[0]:
                print('-', end='')
            else:
                print(base_string[idx], end='')
            return
        for rule in rule_string:
            # print(rule, idx)
            if rule == '$':
                rule_len = len_epoch[level - 1]
                if rule_len <= idx:
                    idx -= rule_len
                else:
                    printIndex(idx, level - 1)
                    return
            else:
                rule_len = len(rule)
                if rule_len <= idx:
                    idx -= rule_len
                else:
                    print(rule[idx], end='')
                    return
        print('-', end='')

    for i in range(min_idx, max_idx + 1):
        printIndex(i - 1, len(len_epoch))
