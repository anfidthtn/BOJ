n = int(input())
for i in range(n):
    print("Case #",i+1,": ",sep='',end='')
    print(*((input().split())[::-1]),sep=' ')