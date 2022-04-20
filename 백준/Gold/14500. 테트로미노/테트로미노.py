import sys
n, m = map(int, sys.stdin.readline().split())

Map = []
Map.append([0] * (m + 6))
Map.append([0] * (m + 6))
Map.append([0] * (m + 6))
for _ in range(n):
    Map.append([0] * 3 + list(map(int, sys.stdin.readline().split())) + [0] * 3)
Map.append([0] * (m + 6))
Map.append([0] * (m + 6))
Map.append([0] * (m + 6))

shapeSet = (
    ((-3, 0), (-2, 0), (-1, 0)), # ㅣ
    ((0, -3), (0, -2), (0, -1)), # ㅡ
    ((-1, -1), (-1, 0), (0, -1)), # ㅁ
    ((-1, -1), (0, -1), (1, 0)), # └┐
    ((1, -1), (0, -1), (-1, 0)), # ┌┘
    ((1, -1), (1, 0), (0, 1)), # ┘┌
    ((-1, -1), (-1, 0), (0, 1)), # ┐└
    ((-2, -1), (-2, 0), (-1, 0)), # ┐
    ((0, -1), (-2, 0), (-1, 0)), # ┘
    ((-2, -1), (-1, -1), (0, -1)), # └
    ((-2, 0), (-1, 0), (-2, 1)), # ┌
    ((-1, -2), (-1, -1), (-1, 0)), # ㅡ┐
    ((-1, -2), (0, -2), (0, -1)), # └ㅡ
    ((0, -2), (0, -1), (-1, 0)), # ㅡ┘
    ((0, -2), (0, -1), (1, -2)), # ┌ㅡ
    ((0, -2), (0, -1), (-1, -1)), # ㅗ
    ((0, -2), (0, -1), (1, -1)), # ㅜ
    ((-2, 0), (-1, 0), (-1, -1)), # ㅓ
    ((-2, 0), (-1, 0), (-1, 1)) # ㅏ
)

maximum = 0
for i in range(3, 3 + n):
    for j in range(3, 3 + m):
        for shape in shapeSet:
            tempSum = Map[i][j]
            for dir in shape:
                tempSum += Map[i + dir[0]][j + dir[1]]
            maximum = max(maximum, tempSum)
print(maximum)