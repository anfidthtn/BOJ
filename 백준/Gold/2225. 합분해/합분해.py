n, k = map(int, input().split())

# 아 10^9구나 10^10해서 한 번 틀렸네..
modular = 10 ** 9

# cases의
# row : 합한 개수 
# col : 합한 결과값
cases = [[0 for _ in range(n + 1)], [1 for _ in range(n + 1)]]

# amount(합한 개수) 정보를 늘려나감
for amount in range(2, k + 1):
    cases.append([0 for _ in range(n + 1)])
    # 0 ~ N까지 타겟넘버로 설정하고 타겟넘버에 대한 계산을 함
    for targetNum in range(n + 1):
        # amount개의 합 targetNum = (amount - 1개의 합 targetNum ~ 0) + (마지막 1개의 0 ~ targetNum) 이므로
        # 아래와 같이 설정가능
        for i in range(targetNum + 1):
            cases[amount][targetNum] += cases[amount - 1][targetNum - i]
        cases[amount][targetNum] %= modular

print(cases[k][n])