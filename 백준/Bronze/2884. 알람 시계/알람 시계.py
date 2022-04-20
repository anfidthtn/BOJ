h, m = map(int, input().split())
if m < 45:
    h = (h + 23) % 24
m = (m + 15) % 60

print(h, m)