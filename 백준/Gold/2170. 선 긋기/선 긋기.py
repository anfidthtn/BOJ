import sys
n = int(sys.stdin.readline())
lines = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
lines.sort()
sections = [[- 10 ** 10, - 10 ** 10]]
for line in lines:
    if sections[-1][1] < line[0]:
        sections.append(line)
    elif sections[-1][1] < line[1]:
        sections[-1][1] = line[1]

result = 0
for section in sections:
    result += section[1] - section[0]
print(result)