import sys

k, n = map(int, input().split())

ks = [int(sys.stdin.readline()) for _ in range(k)]

maxlen = sum(ks) // n

def search(left, right):
    if left == right:
        return left
    sum = 0
    for one in ks:
        sum += one // ((left + right) >> 1)
    
    if sum >= n:
        return search(((left + right) >> 1) + 1, right)
    else:
        return search(left, ((left + right) >> 1))

point = search(1, maxlen)

sum = 0
for one in ks:
    sum += one // point

if sum >= n:
    print(point)
else:
    print(point - 1)