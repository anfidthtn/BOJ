import math
A = input()
B = input()
g = math.gcd(len(A), len(B))
part = A[:g]

def solve():
    for start in range(0, len(A), g):
        for i in range(g):
            if A[start + i] != part[i]:
                return 0
    for start in range(0, len(B), g):
        for i in range(g):
            if B[start + i] != part[i]:
                return 0
    return 1

print(solve())