from collections import deque
import sys

N = int(input())

이름정하기힘들다 = deque()

for _ in range(N):
    order = list(sys.stdin.readline().split())
    if order[0] == 'push_front':
        이름정하기힘들다.appendleft(int(order[1]))
    if order[0] == 'push_back':
        이름정하기힘들다.append(int(order[1]))
    if order[0] == 'pop_front':
        if len(이름정하기힘들다) > 0:
            print(이름정하기힘들다.popleft())
        else:
            print(-1)
    if order[0] == 'pop_back':
        if len(이름정하기힘들다) > 0:
            print(이름정하기힘들다.pop())
        else:
            print(-1)
    if order[0] == 'size':
        print(len(이름정하기힘들다))
    if order[0] == 'empty':
        if len(이름정하기힘들다) == 0:
            print(1)
        else:
            print(0)
    if order[0] == 'front':
        if len(이름정하기힘들다) > 0:
            print(이름정하기힘들다[0])
        else:
            print(-1)
    if order[0] == 'back':
        if len(이름정하기힘들다) > 0:
            print(이름정하기힘들다[-1])
        else:
            print(-1)