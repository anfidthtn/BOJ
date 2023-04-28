A = input()
B = input()
for c in A:
    if c in B:
        print(chr(ord(c) - ord('A') + ord('a')), end='')
    else:
        print(c,end='')