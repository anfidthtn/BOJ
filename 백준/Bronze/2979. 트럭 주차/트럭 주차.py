cost = [0] + list(map(int,input().split()))
count = [0 for i in range(102)]
for i in range(3):
    e, f = map(int,input().split())
    count[e] += 1
    count[f] += -1
ans = 0
for i in range(1, 102):
    count[i] += count[i - 1]
    ans += cost[count[i]] * count[i]
print(ans)