import sys

Q = int(input())
TN = []
TS = []
TE = []
for _ in range(Q):
    n, s, e = map(int, sys.stdin.readline().split())
    TN.append(n)
    TS.append(s)
    TE.append(e)


def F(N, S, E):
    if N < S:
        return 0
    if E < S:
        return 0
    sqN = int(N ** 0.5)
    front = [None]
    sum = 0
    for i in range(1, sqN + 1):
        if E < i:
            break
        front.append(int(N / i))
        if S <= i:
            sum += front[-1]
    
    if front[-1] > S:
        now = sqN + 1
    else:
        while front[-1] < S:
            front.pop()
        if S == sqN:
            now = S + 1
        else:
            now = S
    if now <= E:
        for idx in range(len(front) - 1, 0, -1):
            if front[idx] <= E:
                sum += (front[idx] - now + 1) * idx
                now = front[idx] + 1
                if front[idx] == E:
                    break
            else:
                sum += (E - now + 1) * idx
                break
    return sum

if TN.count(TN[0]) != Q or max(TN) <= 10 ** 6: # sub1, sub2, sub3, sub 5
    for caseNum in range(Q):
        print(F(TN[caseNum], TS[caseNum], TE[caseNum]))
    exit(0)
# if TN.count(TN[0]) != Q: # sub1, sub3, sub 5
#     for caseNum in range(Q):
#         print(F(TN[caseNum], TS[caseNum], TE[caseNum]))
#     exit(0)

# sub4 or (sub4 and sub2)

N = TN[0]

sqN = int(N ** 0.5)

sum = 0

front = [0, N]
frontSum = [0, N]

for i in range(2, sqN + 1):
    front.append(int(N / i))
    frontSum.append(frontSum[-1] + front[-1])

try:
    rear = [0, front[1] - front[2]]
except:
    rear = [0, front[1] - sqN]
for i in range(2, sqN):
    rear.append(front[i] - front[i + 1])
rear.append(front[-1] - sqN)
rearSum = [0] * (sqN + 1)
for i in range(sqN, 0, -1):
    if i == sqN:
        rearSum[i] = rear[i] * i
    else:
        rearSum[i] = rearSum[i + 1] + rear[i] * i

def NF(S, E):
    if N < S: # N < S <= E
        return 0
    if E < S:
        return 0
    
    if E <= sqN: # 1 <= S <= E <= sqN < N
        return frontSum[E] - frontSum[S - 1]

    sum = 0
    if S <= sqN: # 1 <= S <= sqN < E < N
        sum += frontSum[sqN]
        sum -= frontSum[S - 1]
    else:
        if S > sqN + 1:
            target = int(N / (S - 1))
            sum -= rearSum[target]
            sum += (front[target] - (S - 1)) * target

    target = int(N / E)
    sum += rearSum[target]
    sum -= (front[target] - E) * target
    
    return sum
    

for i in range(Q):
    print(NF(TS[i], TE[i]))