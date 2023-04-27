import math
a, b = map(int, input().split(':'))
print(a // math.gcd(a, b), b // math.gcd(a, b), sep=':')