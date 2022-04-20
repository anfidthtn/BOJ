t = int(input())
p = [0, 1, 1, 1, 2, 2, 3, 4, 5, 7, 9]
while len(p) < 101:
    p.append(p[-1] + p[-5])
for _ in range(t):
    print(p[int(input())])