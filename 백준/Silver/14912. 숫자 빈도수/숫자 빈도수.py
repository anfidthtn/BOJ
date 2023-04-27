n, d = map(int, input().split())

counts = [0] * 10
for _ in range(1, n + 1):
    while _ > 0:
        counts[_ % 10] += 1
        _ //= 10

print(counts[d])