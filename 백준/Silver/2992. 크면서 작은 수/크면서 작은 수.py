ssss = [1, 10, 100, 1000, 10000, 100000, 1000000]
ssss2 = [10 ** i for i in range(10)]

x = int(input())

def getNum(x):
    ans = 0
    while x:
        ans += ssss2[x % 10]
        x //= 10
    return ans


def finder(x):
    sss = [0 for _ in range(10)]
    n = x
    while n:
        sss[n % 10] += 1
        n //= 10
    target = getNum(x)
    for y in range(x + 1, ssss[sum(sss)]):
        if target == getNum(y):
            print(y)
            return
    print(0)

finder(x)