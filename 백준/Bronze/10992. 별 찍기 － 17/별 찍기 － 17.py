n = int(input())
for i in range(n - 1):
    for j in range(n + i):
        if j == n + i - 1:
            print('*', end='')
        elif j == n - i - 1:
            print('*', end='')
        else:
            print(' ', end='')
    print()
for _ in range(2 * n - 1):
    print('*', end='')