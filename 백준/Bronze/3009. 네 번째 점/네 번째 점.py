ppp1 = {}
ppp2 = {}
for _ in range(3):
    a, b = map(int, input().split())
    if a in ppp1:
        ppp1[a] += 1
    else:
        ppp1[a] = 1
    if b in ppp2:
        ppp2[b] += 1
    else:
        ppp2[b] = 1
for a in ppp1:
    if ppp1[a] == 1:
        print(a, end=' ')
for b in ppp2:
    if ppp2[b] == 1:
        print(b, end=' ')