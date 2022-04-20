N, S, M = input().split()
N = int(N)
S = int(S)
M = int(M)

V = input().split()
V_set = [{S}]
for idx, v in enumerate(V):
    v = int(v)
    V[idx] = v
    tempSet = set()
    for num in V_set[idx]:
        if num - v >= 0:
            tempSet.add(num - v)
        if num + v <= M:
            tempSet.add(num + v)

    V_set.append(tempSet)
    
    if tempSet == set():
        print(-1)
        break
    
if V_set[-1] != set():
    print(max(V_set[-1]))