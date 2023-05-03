import math
while True:
    n, w, l, h, a, m = map(int, input().split())
    if n + w + l + h + a + m == 0:
        break
    need = 0
    for _ in range(m):
        x, y = map(int, input().split())
        need -= x*y
    need += w * l + w * h * 2 + l * h * 2
    need *= n
    print(math.ceil(need / a))