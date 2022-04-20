n = int(input())

# 0이 될 수 있는 심볼 목록 (초기값 'A' ~ 'J')
zeroAble = set([chr(ord('A') + i) for i in range(10)])

# 각 심볼별 자리값에 따른 가치
count = {}

for _ in range(n):
    s = input()
    # 첫 자리수는 0이 될 수 없다.
    if s[0] in zeroAble:
        zeroAble.remove(s[0])
    for i in range(len(s)):
        # 인풋에 대해 자리값대로 가치를 더해준다.
        if s[i] in count:
            count[s[i]] += 10 ** (len(s) - 1 - i)
        else:
            count[s[i]] = 10 ** (len(s) - 1 - i)

if len(count) == 10:
    # 10개 알파벳이 모두 사용된 경우에는 leadingZero를 생각해야한다.
    # 이 때, 0이 될 수 있는 녀석 중 가장 가치가 낮은 녀석을 0으로 만든다.
    # 12자리수 50개니 아무리 커봐야 10 ** 20 못 넘는다.
    minValue = 10 ** 20
    for zaSymbol in zeroAble:
        if minValue > count[zaSymbol]:
            minValue = count[zaSymbol]
            minSymbol = zaSymbol
    # 가치가 가장 낮은 녀석을 0으로 만든다.(없앰)
    count.pop(minSymbol)

# 가치의 목록만 받아옴
values = list(count.values())
# 정렬(내림차순)
values.sort(reverse=True)

# 합계 계산
totalSum = 0
for digit in range(9, 9 - len(values), -1):
    # 가장 가치 높은 것부터 9, 8, 7, ... 순서로 매핑해서 더함
    totalSum += digit * values[9 - digit]
print(totalSum)