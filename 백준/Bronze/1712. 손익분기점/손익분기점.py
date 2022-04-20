A, B, C = map(int, input().split())
if C - B <= 0:
    print(-1)
    exit()
print(int(A / (C - B) + 1))