import sys
n, m = map(int, input().split())
card = list(map(int, input().split()))
card.sort()

maximum = 0
for a in range(n):
    if card[a] > m:
        break
    for b in range(a + 1, n):
        if card[a] + card[b] > m:
            break
        for c in range(b + 1, n):
            if card[a] + card[b] + card[c] > m:
                break
            maximum = max(maximum, card[a] + card[b] + card[c])
            if maximum == m:
                print(m)
                exit()

print(maximum)