n, m = map(int,input().split())
dna = []
for _ in range(n):
    dna.append(input())

ans = 0
ansdna = ''
#ACGT
dnas = ['A', 'C', 'G', 'T']
for i in range(m):
    c = [0, 0, 0, 0]
    for k in range(4):
        for j in range(n):
            if dna[j][i] != dnas[k]:
                c[k] += 1
    ansdna += dnas[c.index(min(c))]
    ans += min(c)
print(ansdna)
print(ans)