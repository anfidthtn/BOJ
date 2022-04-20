import sys
from collections import deque

m, n = map(int, input().split())

non_count = 0

direction = ((-1, 0), (1, 0), (0, -1), (0, 1))

waiting = deque()

final_day = 0

Box = []
Box.append(['-1'] * (m + 2))
for cn in range(1, n + 1):
    Box.append(['-1'] + list(sys.stdin.readline().split()) + ['-1'])
    for cm in range(1, m + 1):
        if Box[cn][cm] == '0':
            non_count += 1
        elif Box[cn][cm] == '1':
            waiting.append((cn, cm, 0))
            Box[cn][cm] = 0 
Box.append(['-1'] * (m + 2))

while len(waiting) > 0:
    now = waiting.popleft()
    final_day = now[2]

    for dir in direction:
        if Box[now[0] + dir[0]][now[1] + dir[1]] == '0':
            waiting.append((now[0] + dir[0], now[1] + dir[1], now[2] + 1))
            Box[now[0] + dir[0]][now[1] + dir[1]] = now[2] + 1
            non_count -= 1

if non_count > 0:
    print(-1)
else:
    print(final_day)