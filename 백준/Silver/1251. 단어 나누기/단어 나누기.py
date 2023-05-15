word = input()
size = len(word)
l = []
for i in range(1, size):
    for j in range(i + 1, size):
        l.append(word[i - 1::-1] + word[j - 1:i - 1:-1] + word[:j - 1: -1])
l.sort()
print(l[0])