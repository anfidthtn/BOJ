L, P = map(int, input().split())
News = list(map(int, input().split()))

for i in News:
    print(i - L * P, end=' ')