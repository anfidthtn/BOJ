N = int(input())

G = [
    '@   @',
    '@   @',
    '@@@@@',
    '@   @',
    '@@@@@'
]

for i in range(5):
    for m in range(N):
        for j in range(5):
            for n in range(N):
                print(G[i][j], end='')
        print()