st = input()
stack = ['C', 'P', 'C', 'U']
idx = 0
while len(stack) > 0 and idx < len(st):
    if stack[-1] == st[idx]:
        stack.pop()
    idx += 1

if len(stack) > 0:
    print("I hate UCPC")
else:
    print("I love UCPC")