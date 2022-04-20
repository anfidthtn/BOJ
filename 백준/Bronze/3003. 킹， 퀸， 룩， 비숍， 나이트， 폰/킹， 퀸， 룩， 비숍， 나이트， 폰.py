Found = list(map(int, input().split()))
answer = [1, 1, 2, 2, 2, 8]

for i in range(6):
    print(answer[i] - Found[i], end=' ')