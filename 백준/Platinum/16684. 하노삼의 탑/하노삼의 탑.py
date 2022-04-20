from collections import deque

M, N, K = map(int, input().split())

state = [[], deque(range(1, N + 1)), deque(), deque()]


def printResult():
    global N
    global state
    for plateNum in range(1, N + 1):
        for stickNum in range(1, 4):
            if len(state[stickNum]) > 0:
                if state[stickNum][0] == plateNum:
                    state[stickNum].popleft()
                    print(stickNum, end=' ')

def movePlateAtoB(num, A, B):
    global state
    tempDeque = deque()
    while len(state[A]) > 0 and state[A][0] < num:
        tempDeque.append(state[A].popleft())
    while len(tempDeque) > 0:
        state[B].appendleft(tempDeque.pop())


if M == 1:
    move = [-1, 1] + [-1] * (N - 1)
    def getMove(n):
        if move[n] > 0:
            return move[n]
        move[n] = 1 + getMove(n - 1) * 2
        return move[n]


    def moveAtoB(num, A, B):
        global K
        global state
        if K <= 0:
            return
        if num == 1:
            K -= 1
            state[B].appendleft(state[A].popleft())
            return
        if K >= getMove(num - 1):
            K -= getMove(num - 1)
            movePlateAtoB(num, A, 6 - A - B)
            if K > 0:
                K -= 1
                state[B].appendleft(state[A].popleft())
                moveAtoB(num - 1, 6 - A - B, B)
            else:
                return
        else:
            moveAtoB(num - 1, A, 6 - A - B)
    
    moveAtoB(N, 1, 3)


elif M == 2:
    move1 = [-1, 1] + [-1] * (N - 1)
    move2 = [-1, 2] + [-1] * (N - 1)

    def getMove1(n):
        if move1[n] > 0:
            return move1[n]
        move1[n] = 1 + getMove1(n - 1) + getMove2(n - 1)
        return move1[n]
    
    def getMove2(n):
        if move2[n] > 0:
            return move2[n]
        move2[n] = getMove2(n - 1) * 3 + 2
        return move2[n]
    
    def moveSide2Side(num, A):
        global state
        global K
        Mid = 2
        Side = 4 - A

        if K <= 0:
            return
            
        if num == 1:
            K -= 1
            state[Mid].appendleft(state[A].popleft())
            if K > 0:
                K -= 1
                state[Side].appendleft(state[Mid].popleft())
            return

        if K >= getMove2(num - 1):
            K -= getMove2(num - 1)
            movePlateAtoB(num, A, Side)
        else:
            moveSide2Side(num - 1, A)

        if K > 0:
            K -= 1
            state[Mid].appendleft(state[A].popleft())
        else:
            return
        
        if K >= getMove2(num - 1):
            K -= getMove2(num - 1)
            movePlateAtoB(num, Side, A)
        else:
            moveSide2Side(num - 1, Side)

        if K > 0:
            K -= 1
            state[Side].appendleft(state[Mid].popleft())
        else:
            return

        if K >= getMove2(num - 1):
            K -= getMove2(num - 1)
            movePlateAtoB(num, A, Side)
        else:
            moveSide2Side(num - 1, A)
    
    def moveA2Mid(num, A):
        global state
        global K
        Mid = 2
        Side = 4 - A

        if K <= 0:
            return
            
        if num == 1:
            K -= 1
            state[Mid].appendleft(state[A].popleft())
            return

        if K >= getMove2(num - 1):
            K -= getMove2(num - 1)
            movePlateAtoB(num, A, Side)
        else:
            moveSide2Side(num - 1, A)

        if K > 0:
            K -= 1
            state[Mid].appendleft(state[A].popleft())
        else:
            return

        if K >= getMove1(num - 1):
            K -= getMove1(num - 1)
            movePlateAtoB(num, Side, Mid)
        else:
            moveMid2A(num - 1, Side)
    
    def moveMid2A(num, A):
        global state
        global K
        Mid = 2
        Side = 4 - A

        if K <= 0:
            return
            
        if num == 1:
            K -= 1
            state[A].appendleft(state[Mid].popleft())
            return

        if K >= getMove1(num - 1):
            K -= getMove1(num - 1)
            movePlateAtoB(num, Mid, Side)
        else:
            moveMid2A(num - 1, Side)

        if K > 0:
            K -= 1
            state[A].appendleft(state[Mid].popleft())
        else:
            return

        if K >= getMove2(num - 1):
            K -= getMove2(num - 1)
            movePlateAtoB(num, Side, A)
        else:
            moveSide2Side(num - 1, Side)
    
    moveSide2Side(N, 1)
        
    
elif M == 3:
    move1 = [-1, 1] + [-1] * (N - 1)
    move2 = [-1, 2] + [-1] * (N - 1)

    def getMove1(n):
        if move1[n] > 0:
            return move1[n]
        move1[n] = getMove2(n - 1) * 2 + 1
        return move1[n]
    
    def getMove2(n):
        if move2[n] > 0:
            return move2[n]
        move2[n] = getMove1(n - 1) + 2 + getMove2(n - 1) * 2 
        return move2[n]
    
    def moveRight1(num, A):
        global K
        global state
        Ar1 = A % 3 + 1
        Ar2 = (A + 1) % 3 + 1
        if K <= 0:
            return
        if num == 1:
            K -= 1
            state[Ar1].appendleft(state[A].popleft())
            return

        if K >= getMove2(num - 1):
            K -= getMove2(num - 1)
            movePlateAtoB(num, A, Ar2)
        else:
            moveRight2(num - 1, A)
        if K > 0:
            K -= 1
            state[Ar1].appendleft(state[A].popleft())
        else:
            return
        if K >= getMove2(num - 1):
            K -= getMove2(num - 1)
            movePlateAtoB(num, Ar2, Ar1)
        else:
            moveRight2(num - 1, Ar2)
    
    def moveRight2(num, A):
        global K
        global state
        Ar1 = A % 3 + 1
        Ar2 = (A + 1) % 3 + 1
        if K <= 0:
            return
        if num == 1:
            K -= 1
            state[Ar1].appendleft(state[A].popleft())
            if K > 0:
                K -= 1
                state[Ar2].appendleft(state[Ar1].popleft())
            return
        
        if K >= getMove2(num - 1):
            K -= getMove2(num - 1)
            movePlateAtoB(num, A, Ar2)
        else:
            moveRight2(num - 1, A)
        if K > 0:
            K -= 1
            state[Ar1].appendleft(state[A].popleft())
        else:
            return
        if K >= getMove1(num - 1):
            K -= getMove1(num - 1)
            movePlateAtoB(num, Ar2, A)
        else:
            moveRight1(num - 1, Ar2)
        if K > 0:
            K -= 1
            state[Ar2].appendleft(state[Ar1].popleft())
        else:
            return
        if K >= getMove2(num - 1):
            K -= getMove2(num - 1)
            movePlateAtoB(num, A, Ar2)
        else:
            moveRight2(num - 1, A)
        
    moveRight2(N, 1)

printResult()