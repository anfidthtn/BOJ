n = int(input())
nums = list(map(int, input().split()))
nums.sort()

diff = nums[-1] - nums[0]

checkNum = 1
visited = [0 for _ in range(n)]

def check(x):
    global checkNum, visited
    checkNum += 1
    visited[0] = checkNum
    idx = 1
    nextNum = nums[0] + x
    while idx < n:
        if nums[idx] == nextNum:
            visited[idx] = checkNum
            nextNum += x
        idx += 1
    idx = 1
    newDiff = -1
    nextNum = nums[0]
    while idx < n:
        if visited[idx] == checkNum:
            idx += 1
            continue
        if newDiff == -1:
            newDiff = nums[idx] - nums[0]
            nextNum = nums[idx]
        if nums[idx] != nextNum:
            return False
        nextNum += newDiff
        visited[idx] = checkNum
        idx += 1
    if newDiff <= 0:
        return False
    return True

def solve():
    if diff == 0:
        return False
    i = 1
    while i * i <= diff:
        if diff % i == 0:
            if check(i):
                return True
            if check(diff // i):
                return True
        i += 1
    return False

if solve():
    print("Yes")
else:
    print("No")