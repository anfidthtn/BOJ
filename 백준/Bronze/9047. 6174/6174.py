import sys
sys.setrecursionlimit(1 << 20)
ans = {6174 : 0}
n = int(input())

def getNext(x):
    counts = [0] * 10
    n = x
    while n > 0:
        counts[n % 10] += 1
        n //= 10
    counts[0] += 4 - sum(counts)
    M = 0
    for i in range(9, -1, -1):
        for j in range(counts[i]):
            M *= 10
            M += i
    m = 0
    for i in range(0, 10):
        for j in range(counts[i]):
            m *= 10
            m += i
    return M - m

def getans(x):
    global ans
    if x in ans:
        return ans[x]
    a = getans(getNext(x)) + 1
    ans[x] = a
    return ans[x]

for _ in range(n):
    print(getans(int(input())))