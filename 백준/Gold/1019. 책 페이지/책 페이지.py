import sys
dec = [0]*10
# print(dec)

numS = sys.stdin.readline().split('\n')[0]
numL = list(map(int, numS))

length = len(numS)

if int(numS) < 10:
    for i in range(1, int(numS) + 1):
        dec[i] += 1
    for i in range(0, len(dec)):
        print(dec[i], end=' ')
    exit(0)

'''
1~(가장높은 자리수 - 1)의 9로 이루어진 숫자까지 처리
ex) 10 -> 1~9, 333 -> 1 ~ 99, 123011 -> 1 ~ 99999
ex) 98765432이면 가장높은 자리 전인 7번째 자리 9999999까지
9999999~1000000에 들어가는 숫자 수만큼 더해주고
999999~100000
...
9~1까지 숫자 등장 횟수만큼 더해줌
재귀함수로 해도 중첩 얼마 안되니까 편한 재귀로 구현
'''
def nonZeroCount(size):
    if size == 1:
        return
    size -= 1
    for i in range(1, len(dec)):
        dec[i] += int((9 * size + 1) * (10 ** (size - 2))) # 각 자리마다 1~9 개수만큼 더해줌
    dec[0] += int((9 * (size - 1)) * (10 ** (size - 2))) # 각 자리마다 0 개수만큼 더해줌
    nonZeroCount(size)

nonZeroCount(length)

'''
앞에서 처리한 이후부터 가장 높은 자리의 수까지 올리며 처리
ex) 3000 -> 0~999는 앞에서 처리했으니 1000~2999처리, 99922 -> 10000 ~ 89999 처리
1~(맨앞 숫자 - 1)까지 000~999형태를 먼저 처리하고, 개수만큼 1~(맨앞 숫자 -1)까지 더해주는 방식
'''
for i in range(len(dec)):
    dec[i] += (numL[0] - 1) * int((length - 1) * (10 ** (length - 2)))
for d in range(1, numL[0]):
    dec[d] += (10 ** (length - 1))

'''
98765 ->
98760 ~ 98765
98700 ~ 98759
98000 ~ 98699
90000 ~ 97999
순서로 처리하기 위해 역순 리스트 생성
'''
rnumL = [x for x in numL]
rnumL.reverse()


for idx, digit in enumerate(rnumL[:-1]): # 역순 리스트 자리별로 돌면서
    if idx == 0: # 원본 숫자의 1의 자리는 0도 처리해야해서 따로 처리
        for d in rnumL[1:]: # 1의 자리 앞의 10의 자리, 100의 자리, ... 자리 숫자를 digit + 1(0때문에 +1)만큼 더해줌
            dec[d] += digit + 1
        for d in range(digit + 1): # 1의 자리 수 나온만큼 각 숫자 +1 카운팅
            dec[d] += 1
    else:
        for d in rnumL[idx + 1:]: # 1의 자리수때와 마찬가지로 (자기 숫자 * 자기 자리값)만큼 자기 자리보다 높은 숫자 카운팅
            dec[d] += (digit * (10 ** idx))
        for d in range(digit): # 654의 5 부분이라 치면 00 ~ 49까지 10의 자리 카운팅 하는 것
            dec[d] += (10 ** idx)
        for d in range(10): # 654의 5 부분이라 치면 1의 자리가 5(digit)만큼 반복되니 0~9까지 idx * 10^0(idx-1)만큼 더해주는 것
            dec[d] += (idx * (10 ** (idx - 1)) * digit) # 0~9는 10개를 10개 숫자로, 00 ~99는 100개를 200개 숫자로 나타내는 식이라 이런 공식 나옴


for i in dec:
    print(i, end=' ')

# count = [0]*10
# number = int(numS)
# for i in range(1, number + 1):
#     tempL = list(map(str, str(i)))
#     for num in range(10):
#         count[num] += tempL.count(str(num))
# print(count)