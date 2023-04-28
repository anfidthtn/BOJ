days = [0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
n = int(input())
for _ in range(n):
    x, y = map(int, input().split())
    print("Yes" if x < 24 and y < 60 else "No", "Yes" if x <= 12 and 0< y <= days[x] else "No")