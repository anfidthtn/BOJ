n = int(input())
ans = [1, 1, 3]
while len(ans) <= n:
    ans.append((ans[-1] + ans[-2] + 1) % 1000000007)
print(ans[n])