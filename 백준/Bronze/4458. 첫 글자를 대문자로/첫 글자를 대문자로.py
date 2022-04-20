def upp(str):
    if 'a' <= str[0] and str[0] <= 'z':
        str[0] = chr(ord(str[0]) + ord('A') - ord('a'))
    for i in range(len(str)):
        print(str[i], end='')
    print()


n = int(input())
for _ in range(n):
    str = list(input())
    upp(str)