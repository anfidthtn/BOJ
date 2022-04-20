import sys
N = int(input())

height = [0]
count = 0

def newY(y):
    global count
    while y < height[-1]:
        height.pop()
    if y > height[-1]:
        height.append(y)
        count += 1

for _ in range(N):
    x, y = sys.stdin.readline().split()
    y = int(y)
    newY(y)
newY(0)
print(count)