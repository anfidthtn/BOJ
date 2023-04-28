while True:
    s = input()
    if s == '#':
        break
    a = s[0]
    b = s[2:]
    print(a, b.count(a) + b.count(chr(ord(a) + ord('A') - ord('a'))))