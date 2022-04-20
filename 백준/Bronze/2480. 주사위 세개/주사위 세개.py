A, B, C = map(int, input().split())

if A == B and B == C:
    print(10000 + A * 1000)
elif A == B:
    print(1000 + A * 100)
elif B == C:
    print(1000 + B * 100)
elif C == A:
    print(1000 + C * 100)
else:
    m = A
    if m < B:
        m = B
    if m < C:
        m = C
    print(m * 100)