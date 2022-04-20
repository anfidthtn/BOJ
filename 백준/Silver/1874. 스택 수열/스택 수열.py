import sys
n = int(input())
aim = [int(sys.stdin.readline()) for _ in range(n)]
order = []
inputNum = 1
stack = []
for num in aim:
    while inputNum <= num:
        stack.append(inputNum)
        inputNum += 1
        order.append('+')
    if stack[-1] != num:
        print('NO')
        exit()
    else:
        stack.pop()
        order.append('-')
for o in order:
    print(o)