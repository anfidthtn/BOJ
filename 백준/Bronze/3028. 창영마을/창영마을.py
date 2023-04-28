ball = [0, 1, 0, 0]

for c in input():
    if c == 'A':
        ball[1], ball[2] = ball[2], ball[1]
    elif c == 'B':
        ball[2], ball[3] = ball[3], ball[2]
    else:
        ball[1], ball[3] = ball[3], ball[1]

print(ball.index(1))