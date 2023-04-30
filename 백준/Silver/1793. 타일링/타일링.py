import sys
ans=[1,1,3]
lines = sys.stdin.readlines()
for line in lines:
    n = int(line)
    while len(ans) <= n:
        ans.append(ans[-1] + ans[-2] * 2)
    print(ans[n])