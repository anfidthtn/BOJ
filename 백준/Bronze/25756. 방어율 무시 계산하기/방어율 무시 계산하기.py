total = 100
input()
for i in map(int,input().split()):
    total *= (100 - i) / 100
    print(100 - total)