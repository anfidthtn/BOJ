import sys
total = 0
prev = []
for _ in range(int(sys.stdin.readline())):
    n = int(sys.stdin.readline())
    if n == 0:
        total -= prev.pop()
    else:
        prev.append(n)
        total += n
print(total)