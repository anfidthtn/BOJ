n = int(input())
for i in range(n):
    for j in range(1, n - i):
        print(" ", end='')
    for j in range(n - i - 1, n):
        print("*", end='')
    print()
for i in range(n - 2, -1, -1):
    for j in range(1, n - i):
        print(" ", end='')
    for j in range(n - i - 1, n):
        print("*", end='')
    print()