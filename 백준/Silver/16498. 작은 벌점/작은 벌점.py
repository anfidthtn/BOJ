input()
cards = []
for i in range(3):
  cards += list(map(lambda x: [int(x), i], input().split()))
cards.sort(key=lambda x: x[0])
last = [10 ** 10] * 3
answer = 10 ** 10
for card in cards:
  last[card[1]] = card[0]
  answer = min(answer, max(last) - min(last))
print(answer)