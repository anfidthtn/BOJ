N, M = map(int, input().split())
answer = []
SN = str(N)
while len(answer) < len(SN) * N and len(answer) < M:
  answer.append(SN[len(answer) % len(SN)])

print(*answer, sep='')