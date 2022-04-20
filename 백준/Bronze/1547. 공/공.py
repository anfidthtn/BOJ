m = int(input())
ball = [0, 1, 0, 0]
for _ in range(m):
    x, y = map(int, input().split())
    ball[x], ball[y] = ball[y], ball[x]
print(ball.index(1))