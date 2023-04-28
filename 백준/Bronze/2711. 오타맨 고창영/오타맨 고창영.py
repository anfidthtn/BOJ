n=int(input())
for _ in range(n):
    n, s = input().split()
    n = int(n) - 1
    for i in range(len(s)):
        if i != n:
            print(s[i], end='')
    print()