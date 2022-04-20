import sys
n = int(input())

stack = []

for _ in range(n):
    order = list(sys.stdin.readline().split())
    if order[0] == 'push':
        stack.append(order[1])
    if order[0] == 'pop':
        if len(stack) > 0:
            print(stack.pop())
        else:
            print(-1)
    if order[0] == 'size':
        print(len(stack))
    if order[0] == 'empty':
        if len(stack) > 0:
            print(0)
        else:
            print(1)
    if order[0] == 'top':
        if len(stack) > 0:
            print(stack[-1])
        else:
            print(-1)