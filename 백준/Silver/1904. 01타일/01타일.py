ans = [0, 1, 2]
n = int(input())
while len(ans) <= n:
    ans.append((ans[-1] + ans[-2]) % 15746)
print(ans[n])