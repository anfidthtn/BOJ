a = int(input())
b = int(input())
c = int(input())
s = a * b * c
counts = [0 for _ in range(10)]
while s:
    counts[s % 10] += 1
    s //= 10

print(*counts, sep='\n')