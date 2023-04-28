target = input()
ans=0
n = int(input())
for _ in range(n):
    ring = input()*2
    if target in ring:
        ans += 1

print(ans)