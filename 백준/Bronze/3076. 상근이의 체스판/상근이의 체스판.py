r, c = map(int,input().split())
a, b = map(int,input().split())
arr = [['X' if (x + y) & 1 == 0 else '.' for y in range(c)] for x in range(r)]

for i in range(r * a):
    for j in range(c * b):
        print(arr[i // a][j // b], end='')
    print()