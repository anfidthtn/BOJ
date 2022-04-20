n = int(input())
n = list(str(n))
for i in range(9, -1, -1):
    for _ in range(n.count(chr(i + ord('0')))):
        print(i, end='')