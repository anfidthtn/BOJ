n, sc, p = map(int,input().split())
if n > 0:
    ps = list(map(int,input().split()))
    ps.append(sc)
    ps.sort(reverse=True)
    idx = -1
    for i in range(len(ps) - 1, -1, -1):
        if ps[i] == sc:
            idx = i
            break
    if idx >= p:
        print(-1)
    else:
        print(1 + ps.index(sc))
else:
    print(1)