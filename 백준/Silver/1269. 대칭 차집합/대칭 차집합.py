a, b = map(int, input().split())
A = set(map(int, input().split()))
ans = a + b
for num in map(int, input().split()):
    if num in A:
        ans -= 2
print(ans)