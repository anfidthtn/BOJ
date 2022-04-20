now = list(input())
for i in range(len(now)):
    now[i] = int(now[i])
now.reverse()
after = now.copy()
lines = [6, 2, 5, 5, 4, 5, 6, 3, 7, 5]

# 1~9 까지, 01~99까지, 001~999까지, ... 의 합의 조합
lineComb = [0] + [[2 * i, 7 * i] for i in range(1, len(now) + 1)]
def getLine(one):
    count = 0
    for i in one:
        count += lines[i]
    return count

def plusOne(one, digit):
    if digit == len(one):
        return 0
    
    if one[digit] < 9:
        one[digit] = one[digit] + 1
    else:
        one[digit] = 0

        return plusOne(one, digit + 1)
    return digit

# 1356 들어왔다 치면
# 1357
# 1358
# 1359
# 1360 ~ 1369
# 1370 ~ 1379
# ...
# 1400 ~ 1499
# 1500 ~ 1599 여기서 가능한 조합이 나오면
# 1501
# 1502
# ...
# 1510 ~ 1519 여기서 가능한 조합이 나오면
# 1511
# 1512
# ... 으로 찾음
# 만약 9000 ~ 9999까지 못 찾으면
# 0000부터 0001 0002 ... 0010 ~ 19 ... 0100 ~ 199 ... 1000 ~ 1999 ... 순으로 찾아나감

plusOne(after, 0)
count = 1
digit = 0
while True:
    remain = getLine(after) - getLine(now)
    if remain == 0:
        break
    if digit == 0:
        # 경우의 수를 못 찾았으니 다음 수 탐색
        # 만약 9 -> 10이 되면 digit이 1이 반환되어서 다음엔 10의 자리 단위에서 탐색함
        count += 10 ** digit
        digit = plusOne(after, digit)
    else:
        # 위의 예시 1360 ~ 1399 의 10의 자리수 점점 자리수 올려가며 볼 차례
        # 예시에서 remain에 '6''0' - '5''6' 이 들어오면 '0'자리에 1~9를 넣어보며 비교할 수 있음
        # '5''0''0' - '3''5''6' 이면 '0''0' 자리에 01 ~ 99를 넣어보며 비교할 수 있음
        # 그래서 lines[0] * digit을 remain에서 빼 줌으로서 01~99 시리즈를 넣을 자리 확보
        # 500 - 356 예시에서 5xx - 356이 된 상태로 xx에 01~99에서 올 수 있는 값을 모두 했을 때 0이 나오는지 안 나오는지 여부 판별해야함
        # lineComb[digit]의 좌측엔 1~9, 01~99, 001~999 ... 의 최소합계, 우측엔 최고합계가 있음
        if remain - lines[0] * digit + lineComb[digit][-1] < 0 or remain - lines[0] * digit + lineComb[digit][0] > 0:
            # 이 경우는 최고 합계를 더해도 0 밑으로만 나오고 최저 합계를 더해도 0위로만 나와서 만족하는 경우가 없을 때이다.
            # 그러면 이번 자리수를 더한다.
            count += 10 ** digit # 카운팅 미리 안해주면 digit 바뀌어서 잘못 올라가는 경우 생길 수 있음
            digit = plusOne(after, digit)
        else:
            # 그 사이범위에 있어서 만족하는 경우가 있다고 치자.
            # 만족하는 경우가 있으면 이전 자리로 탐색범위를 좁힌다.
            # 500 ~ 599에서 만족 -> 500 ~ 509 탐색, 510 ~ 519 탐색, ...
            digit -= 1

# print(now, after)
print(count)