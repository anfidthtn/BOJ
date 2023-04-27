n=int(input())
ans = []
while n > 0:
    ans.append(n % 2)
    n >>= 1
ans = ans[::-1]
print(*ans, sep='')