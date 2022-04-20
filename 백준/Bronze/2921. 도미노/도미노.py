setSize = [0]
n = int(input())
while len(setSize) <= n:
    setSize.append(setSize[-1] + 3 * len(setSize) * (len(setSize) + 1) // 2)
print(setSize[-1])