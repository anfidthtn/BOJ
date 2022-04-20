n = int(input())
a = list(map(int, input().split()))
b = list(map(int, input().split()))
a.sort()
b.sort(reverse=True)
t = 0
for i in range(n):
    t += a[i] * b[i]
print(t)