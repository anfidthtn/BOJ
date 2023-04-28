n, m, k = map(int,input().split())
if k < n + m - 1:
    print("NO")
else:
    print("YES")
    for i in range(1, n + 1):
        for j in range(0, m):
            print(i + j, end=' ' if j != m - 1 else '')
        print()