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

d, m = map(int, input().split())
dN = list(map(int, input().split()))
mN = list(map(int, input().split()))

dLcm = LCMNUMS(dN)
mGcd = GCDNUMS(mN)

if mGcd < dLcm or mGcd % dLcm > 0:
    print(0)
    exit()

result = mGcd // dLcm

primeMax = int(result ** 0.5) + 3
isPrime = [True] * primeMax
prime = []
for i in range(2, primeMax):
    if isPrime[i] is False:
        continue
    prime.append(i)
    for j in range(i * 2, primeMax, i):
        isPrime[j] = False

count = 1
for p in prime:
    expo = 0
    while result % p == 0:
        expo += 1
        result //= p
    count *= expo + 1
if result > 1:
    count *= 2
print(count)