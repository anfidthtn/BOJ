n = int(input())
n = (n - 1) % 8 + 1
print(min(n, 10 - n))