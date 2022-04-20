exp = input()
op = []
for ch in exp:
    if 'A' <= ch and ch <= 'Z':
        print(ch, end='')
        if op and (op[-1] == '*' or op[-1] == '/'):
            print(op.pop(), end='')
    elif ch == '(':
        op.append(ch)
    elif ch == ')':
        while op:
            top = op.pop()
            if top == '(':
                break
            print(top, end='')
    elif ch == '*' or ch == '/':
        if op and (op[-1] == '*' or op[-1] == '/'):
            print(op.pop(), end='')
        op.append(ch)
    elif ch == '+' or ch == '-':
        while op:
            if op[-1] == '(':
                break
            print(op.pop(), end='')
        op.append(ch)
while op:
    print(op.pop(), end='')