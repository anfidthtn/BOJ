n = int(input())
count = 0
for _ in range(n):
    word = input()
    alpha_set = set()
    isGroup = True
    while len(word) > 0:
        if word[0] in alpha_set:
            isGroup = False
            break
        alpha_set.add(word[0])
        word = word.lstrip(word[0])
    if isGroup is True:
        count += 1
print(count)