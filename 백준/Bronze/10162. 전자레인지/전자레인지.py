a = int(input())
if a % 10 != 0:
    print(-1)
    exit()
count = 0
while a >= 300:
    count += 1
    a -= 300
print(count, end=' ')
count = 0
while a >= 60:
    count += 1
    a -= 60
print(count, end=' ')
count = 0
while a >= 10:
    count += 1
    a -= 10
print(count, end=' ')