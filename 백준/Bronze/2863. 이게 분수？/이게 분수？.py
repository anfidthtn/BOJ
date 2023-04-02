a,b=map(int,input().split())
c,d=map(int,input().split())

ans = 0
target = a / c + b / d
a, b, d, c = c, a, b, d
if target < a / c + b / d:
    target = a / c + b / d
    ans = 1
a, b, d, c = c, a, b, d
if target < a / c + b / d:
    target = a / c + b / d
    ans = 2
a, b, d, c = c, a, b, d
if target < a / c + b / d:
    target = a / c + b / d
    ans = 3
print(ans)