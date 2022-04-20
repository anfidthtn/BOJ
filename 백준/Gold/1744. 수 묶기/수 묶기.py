import sys
from collections import deque
'''
음수와 0 : 절댓값이 가장 큰 것부터 2개씩 묶어서 곱한 후 더함. 홀수 개일 경우 절댓값이 가장 작은 것은 음수일지라도 그냥 더해야 함
1 : 그냥 더함
2 이상 : 절댓값이 가장 큰 것부터 2개씩 묶어서 곱한 후 더함. 홀수 개일 경우 절댓값이 가장 작은 것은 그냥 더함.
이걸 그냥 for문이나 while문 2개로 인덱스 늘리고 줄이고 하면서 탐색할 수 있긴 한데, 요구하는 N값도 작고 새벽에 머리 더 쓰기도 귀찮아서 그냥 덱으로 대충 구현
'''

nums = []
for _ in range(int(sys.stdin.readline())):
    nums.append(int(sys.stdin.readline()))
nums.sort()

count = 0
plusDeque = deque()
minusDeque = deque()
while len(nums) > 0:
    if nums[-1] >= 2:
        plusDeque.append(nums.pop())
    elif nums[-1] == 1:
        count += 1
        nums.pop()
    else:
        minusDeque = deque(nums)
        break
while len(plusDeque) >= 2:
    count += plusDeque.popleft() * plusDeque.popleft()
if len(plusDeque) == 1:
    count += plusDeque[0]

while len(minusDeque) >= 2:
    count += minusDeque.popleft() * minusDeque.popleft()
if len(minusDeque) == 1:
    count += minusDeque[0]
print(count)