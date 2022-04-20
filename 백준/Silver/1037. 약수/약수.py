def GCD(a, b = None):
    if b is None:
        return a
    while b > 0:
        temp = a % b
        a = b
        b = temp
    return a

def GCDNUMS(nums):
    gcd = None
    for num in nums:
        gcd = GCD(num, gcd)
    return gcd

def LCM(a, b = None):
    if b is None:
        return a
    return a * b // GCD(a, b)

def LCMNUMS(nums):
    lcm = None
    for num in nums:
        lcm = LCM(num, lcm)
    return lcm

input()
nums = list(map(int, input().split()))

print(GCDNUMS(nums) * LCMNUMS(nums))