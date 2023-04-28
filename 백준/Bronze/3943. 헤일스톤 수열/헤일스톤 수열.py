import sys
input = sys.stdin.readline
ans = {1 : 1}

def getAns(x):
    if x in ans:
        return ans[x]
    ans[x] = max(x, getAns(x * 3 + 1 if x & 1 else x // 2))
    return ans[x]

for _ in range(int(input())):
    print(getAns(int(input())))