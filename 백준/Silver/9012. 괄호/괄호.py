import sys
for _ in range(int(sys.stdin.readline())):
    s = sys.stdin.readline().rstrip()
    result = 'YES'
    stack = []
    for c in s:
        if c == '(':
            stack.append(c)
        else:
            if not stack:
                result = 'NO'
                break
            elif stack[-1] != '(':
                result = 'NO'
                break
            else:
                stack.pop()
    if result == 'YES' and stack:
        result = 'NO'
    print(result)