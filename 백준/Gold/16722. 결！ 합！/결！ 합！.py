import sys
info = [None] * 10
for i in range(1, 10):
    info[i] = list(sys.stdin.readline().rstrip().split())

def isHap(a, b, c):
    for col in range(3):
        if info[a][col] == info[b][col]:
            if info[a][col] != info[c][col]:
                return False
        else:
            if info[a][col] == info[c][col]:
                return False
            if info[b][col] == info[c][col]:
                return False
    return True


hapSet = set()

for a in range(1, 10):
    for b in range(a + 1, 10):
        for c in range(b + 1, 10):
            if isHap(a, b, c) is True:
                hapSet.add((a, b, c))

playerScore = 0

alreadyG = False

n = int(sys.stdin.readline())
for _ in range(n):
    playing = list(sys.stdin.readline().rstrip().split())
    if playing[0] == 'G':
        if len(hapSet) == 0 and alreadyG is False:
            playerScore += 3
            alreadyG = True
        else:
            playerScore -= 1
    if playing[0] == 'H':
        a, b, c = int(playing[1]), int(playing[2]), int(playing[3])
        abc = [a, b, c]
        abc.sort()
        abcT = tuple(abc)
        if abcT in hapSet:
            playerScore += 1
            hapSet.remove(abcT)
        else:
            playerScore -= 1
print(playerScore)