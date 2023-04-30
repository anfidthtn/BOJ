from itertools import permutations
n = int(input())
k = int(input())
cards = []
for _ in range(n):
    cards.append(int(input()))

ans = set()
for a in permutations(cards, k):
    nnn = 0
    for aa in a:
        while aa > 0:
            nnn *= 10
            nnn += aa % 10
            aa //= 10
    ans.add(nnn)


print(len(ans))