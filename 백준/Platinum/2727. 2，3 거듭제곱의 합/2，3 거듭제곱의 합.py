import sys

T = int(input())

result = []
def find(n, c2, c3):
    if n == 0:
        return
    if n == 1:
        result.append([c2, c3])
        return
    if n % 2 == 0:
        find(int(n / 2), c2 + 1, c3)
    else:
        r3 = 0
        while 3 ** (r3 + 1) <= n:
            r3 += 1
        result.append([c2, r3])
        find(n - 3 ** r3, c2, 0)

for _ in range(T):
    case = int(sys.stdin.readline())
    find(case, 0, 0)
    print(len(result))
    while len(result) > 0:
        print(result[-1][0], result[-1][1])
        result.pop()