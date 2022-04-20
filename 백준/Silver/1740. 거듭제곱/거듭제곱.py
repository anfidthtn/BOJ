N = int(input())
binList = list(bin(N))[2:]
binList.reverse()

sum = 0
for idx, digit in enumerate(binList):
    if digit == '1':
        sum += 3 ** idx

print(sum)