찍=[['A','B','C','A','B','C'],['B','A','B','C'],['C','C','A','A','B','B']]
n = int(input())
anss = input()
counts = [0,0,0]
names = ['Adrian','Bruno','Goran']
for i in range(n):
    for j in range(3):
        if anss[i] == 찍[j][i % len(찍[j])]:
            counts[j] += 1
print(max(counts))
for i in range(3):
    if counts[i] == max(counts):
        print(names[i])