import math
n = int(input())
식단 = input()
치킨 = 식단.count('C')
나머지 = len(식단) - 치킨
print(math.ceil(치킨 / (나머지 + 1)))