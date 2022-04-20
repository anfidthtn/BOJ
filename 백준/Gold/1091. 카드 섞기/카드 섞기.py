N = int(input())
goal = list(map(int, input().split()))
rule = list(map(int, input().split()))


def one_move(now):
    next = [0] * N
    for idx, i in enumerate(rule):
        next[i] = now[idx]
    return next


def is_same_state(now, goal):
    for idx, i in enumerate(now):
        if goal[i] != idx % 3:
            return False
    return True


nowCard = [x for x in range(N)]
initState = [x % 3 for x in range(N)]

count = 0
while is_same_state(nowCard, goal) is False:
    count += 1
    nowCard = one_move(nowCard)
    if is_same_state(nowCard, initState):
        print(-1)
        exit(0)

print(count)