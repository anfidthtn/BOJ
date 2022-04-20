l, u = map(int, input().split())

digitSum = [0, 45]

for i in range(2, 12):
    temp = digitSum[i - 1] * 10
    temp += digitSum[1] * 10 ** (i - 1)
    digitSum.append(temp)

def getAccDigitSum(num):
    if num <= 0:
        return 0
    expo = len(str(num)) - 1

    result = 0
    first = num // 10 ** expo
    last = num % 10 ** expo

    result += first * digitSum[expo] # 7123은 맨 앞이 0 ~ 6일 때 각각 뒤에 001 ~ 999 가 1번씩 나옴, 즉 7번의 000 ~ 999 를 더해줌
    for i in range(1, first):
        result += i * 10 ** expo # 7123은 맨 앞자리에서 1 ~ 6까지 1000번씩 나온 것을 더해줌
    result += first * (last + 1) # 7123은 맨 앞자리 7이 124번 나오니까 해당 횟수 더해줌
    result += getAccDigitSum(last) # 0000 ~ 6999와, 7이 124번 나온 것 처리했으니, 이제 123에 대해서 다시 처리
    return result

print(getAccDigitSum(u) - getAccDigitSum(l - 1))