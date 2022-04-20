MAXNUM = 10 ** 9 + 7
def getExpoMod(x, expo):
    result = 1
    while expo > 0:
        if expo % 2 != 0:
            result *= x
            result %= MAXNUM
        x *= x
        x %= MAXNUM
        expo = int(expo / 2)
    return result

n, k = map(int, input().split())
if k == 0 or k == n:
    print(1)
    exit()

def getFact(nums):
    result = [1, 0, 0, 0]
    for i in range(1, 4):
        if nums[i] <= 1:
            result[i] = 1
            continue
        temp = result[i - 1]
        for j in range(nums[i - 1] + 1, nums[i] + 1):
            temp *= j
            temp %= MAXNUM
        result[i] = temp
    return result

nums = [0, n, n-k, k]
nums.sort()
fact = getFact(nums)

print((fact[3] * getExpoMod((fact[1] * fact[2]) % MAXNUM, MAXNUM - 2)) % MAXNUM)