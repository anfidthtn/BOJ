from collections import deque
n = int(input())
'''
1 : 1 * 2 블록
2 : 2 * 1 블록 2개 쌓은 모양
-2 : 2 * 2 블록
mirror
2 => 1, 1 / 2 / -2
3 => 1, 1, 1
4 => 1, 1, 1, 1 / 1, 2, 1 / 1, -2, 1 / 2, 2 / -2, -2
5 => 1, 1, 1, 1, 1 / 2, 1, 2 / -2, 1, -2
6 => 1, 1, ..., 1 / 1, 1, 2, 1, 1 / 1, 2, 2, 1 / 2, 1, 1, 2 / 2, 2, 2 /
          1, 1, -2, 1, 1 / 1, -2, -2, 1 / -2, 1, 1, -2 / -2, -2, -2
이런식으로 해당 mirror는 대칭인 경우
'''
mirror = [
    [deque()],
    [deque([1])],
    [deque([1, 1]), deque([2]), deque([-2])],
    [deque([1, 1, 1])]
]

for i in range(4, n + 1):
    mirror.append([])
    for mirrorCase in mirror[i - 2]:
        nowCase = mirrorCase.copy()
        nowCase.appendleft(1)
        nowCase.append(1)
        mirror[-1].append(nowCase)
    for mirrorCase in mirror[i - 4]:
        nowCase = mirrorCase.copy()
        nowCase.appendleft(2)
        nowCase.append(2)
        mirror[-1].append(nowCase)
        nowCase = mirrorCase.copy()
        nowCase.appendleft(-2)
        nowCase.append(-2)
        mirror[-1].append(nowCase)

fullCase = [0, 1, 3]
for i in range(3, n + 1):
    fullCase.append(fullCase[i - 2] * 2 + fullCase[i - 1])

print((fullCase[n] + len(mirror[n])) // 2)
