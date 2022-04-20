import sys
n = int(input())
if n == 1:
    print(0)
    exit()

MAXNUM = 10 ** 9 + 7
def getModInverse(x):
    modInverse = 1
    expo = MAXNUM - 2
    while expo > 0:
        if expo % 2 != 0:
            modInverse *= x
            modInverse %= MAXNUM
        x *= x
        x %= MAXNUM
        expo = int(expo / 2)
    return modInverse
'''
5의 배수니까 1의 자리는 0 or 5이지만 1, 5만 써야하니 무조건 5
10의 자리 이상에서는 1을 선택하면 전체 자리수 합이 1,
5를 선택하면 전체 자리수 합이 5 증가함
예를 들어, 5자리 수라고 가정하면
(1 or 5) (1 or 5) (1 or 5) (1 or 5) 5 이렇게인데
모두 1을 고르면 (n - 1) + 5 에서 시작
여기서 n - 1개의 선택지 중에서 k개의 5를 고르면  (n - 1) + 5 + 4 * k 가 자리합이 됨
자리합이 3의 배수이면 3의 배수이니 15의 배수가 됨
k를 올려가며 15의 배수임이 확정되면 (n-1)C(k) 를 더해주면 됨
'''
fact = [1] * (n + 1)
for i in range(1, n + 1):
    fact[i] = (fact[i - 1] * i) % MAXNUM

count = 0
for k in range(n): # k(5 고르기)를 0 ~ n - 1까지
    if (n + 4 + 4 * k) % 3 == 0:
        count += (fact[n - 1] * getModInverse((fact[n - 1 - k] * fact[k]) % MAXNUM)) % MAXNUM
        count %= MAXNUM

print(count)