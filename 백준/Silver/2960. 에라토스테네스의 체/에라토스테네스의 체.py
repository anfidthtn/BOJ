N, K = map(int, input().split())
Num = [False, False] + [True for i in range(2, N + 1)]

for i in range(2, N + 1):
    if Num[i] is True:
        K -= 1
        if K == 0:
            print(i)
            exit()
        for j in range(i * 2, N + 1, i):
            if Num[j] is True:
                K -= 1
                if K == 0:
                    print(j)
                    exit()
                Num[j] = False