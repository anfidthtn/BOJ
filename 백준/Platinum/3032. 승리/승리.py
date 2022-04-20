N = int(input())
originField = list(map(int, input().split()))

fieldIdxToMaxScore = [[-N for _ in range(N)] for _ in range(N)]
for i in range(N):
    fieldIdxToMaxScore[i][i] = originField[i] % 2


def getMaxScore(leftIdx, rightIdx):
    if fieldIdxToMaxScore[leftIdx][rightIdx] > -N:
        return fieldIdxToMaxScore[leftIdx][rightIdx]
    
    nowScore = originField[leftIdx] % 2 - getMaxScore((leftIdx + 1) % N, rightIdx)
    if nowScore > fieldIdxToMaxScore[leftIdx][rightIdx]:
        fieldIdxToMaxScore[leftIdx][rightIdx] = nowScore
    nowScore = originField[rightIdx] % 2 - getMaxScore(leftIdx, (rightIdx - 1 + N) % N)
    if nowScore > fieldIdxToMaxScore[leftIdx][rightIdx]:
        fieldIdxToMaxScore[leftIdx][rightIdx] = nowScore
    return fieldIdxToMaxScore[leftIdx][rightIdx]

count = 0
for i in range(N):
    if originField[i] % 2 - getMaxScore((i + 1) % N, (i - 1 + N) % N) > 0:
        count += 1
print(count)