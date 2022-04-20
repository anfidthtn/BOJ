import math
n = int(input())
st = list(map(int, input().split()))
b, c = map(int, input().split())
count = n
for num in st:
    num -= b
    if num > 0:
        count += math.ceil(num / c)
print(count)