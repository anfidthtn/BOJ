problem = input()

fragCount = 0
pipeStacked = 0

idx = 0
while idx < len(problem) - 1:
    now = problem[idx]
    next = problem[idx + 1]
    if now == '(':
        if next == ')':
            fragCount += pipeStacked
            idx += 2
        else:
            pipeStacked += 1
            idx += 1
    else:
        fragCount += 1
        pipeStacked -= 1
        idx += 1

print(fragCount + 1)