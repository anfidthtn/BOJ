# [1, B] 범위의 A의 배수 중 다음 줄에 주어지는 N개의 비트(우측에서부터 0 ~ 30 번째 비트)가 모두 1인 A의 배수의 개수 구하기
N, A, B = map(int, input().split())
# 주어지는 N개의 비트만 켜졌을 때 판정용 저장변수
mask = 0

# 특정 범위에서 count를 세는 함수
# ex) bit가 3이면
# ... 0000 0000
# ... 7654 3210 이니까 오른쪽에서 4번째 비트이다
# ... xxxx 0000 = (xxxxxx) + 0(0000)
# ... xxxx 1111 = (xxxxxx) + 15(1111)
# 여기에서 left 가 (xxxxxx) + 0, right 가 (xxxxxx) + 15가 되는 구조
# 최초 시작할 때는 30번 비트부터 시작하여
# 0 000 0000 0000 0000 0000 0000 0000 0000 (0)
# 0 111 1111 1111 1111 1111 1111 1111 1111 (2147483647)의 범위를 보고
# bit를 낮춰가며 0 ~ 1073741823, 1073741824 ~ 2147483647 범위를 보고
# ... 이렇게 진행
# 그러다가 1 ~ B 범위 밖으로 아예 벗어나면 0 반환
# 1 ~ B 범위에 포한되는 범위이면 해당 부분은 구한 다음 저장 (단, 여기까지 오며 앞에서 구한 나머지값과 함께 저장)
def getCount(bit:int, left:int, right:int):
  global mask, A, B, counts
  r = left % A
  # 특정 범위가 [1, B] 범위에 속하지 않으면 0을 리턴
  if right < 1 or B < left:
    return 0
  # 마지막 비트까지 모두 채워서 직접 배수판단을 할 수 있으면 배수판단해서 1 or 0 리턴
  if left == right:
    # left == right == 현재 판정할 수
    if left % A == 0:
      return 1
    else:
      return 0
  # [1, B] 범위 안에 특정 범위가 완벽히 속해있다면 미리 구해둔 것이 있는지 확인해서 반환하거나 구해서 저장후 반환 가능
  if 1 <= left and right <= B:
    # bit와 시작 수가 같으면 범위가 같기 때문에
    # bit와 시작 수의 나머지로 저장
    if counts[bit][r] >= 0:
      # 저장한 것이 있으면 그대로 반환
      return counts[bit][r]
    # 없으면 계산 시작
    res = 0
    # mask에서 현재 넣어볼 bit 자리가 1이면 무조건 1만 되지만, 0이면 0도 된다.
    if mask & (1 << bit) == 0:
      # 0도 되는 경우 0을 넣은 경우의 수를 구한다.
      res += getCount(bit - 1, left, (left + right) // 2)
    # 1을 넣은 경우의 수를 구한다.
    res += getCount(bit - 1, (left + right) // 2 + 1, right)
    # 저장하고 반환한다.
    counts[bit][r] = res
    return counts[bit][r]
  else:
    # [1, B] 범위에 완벽히 포함은 되지 않으나 일부가 있는 경우 저장하지 않고 단순 계산만 한다.
    res = 0
    # 이 부분은 위와 동일
    if mask & (1 << bit) == 0:
      res += getCount(bit - 1, left, (left + right) // 2)
    res += getCount(bit - 1, (left + right) // 2 + 1, right)
    return res


def solve():
  global mask, counts
  # 마스킹할 bit가 0개면 그냥 B를 A로 나눈 정수몫만 구하면 된다.
  if N == 0:
    print(B // A)
    return
  for _ in range(N):
    mask += 1 << int(input())
  # 숫자가 어느 정도 크다면 단순 완전탐색으로 파악할 수 있으니 해당 부분은 완전탐색으로 한다.
  if A > 2 ** 12:
    count = 0
    for i in range(A, B + 1, A):
      # i & mask 해서 mask가 나왔다는 것은 i가 mask의 비트를 모두 포함하고 있다는 것 (조건만족)
      if (i & mask) == mask:
        count += 1
    print(count)
    return
  # 숫자가 어느 정도보다 작다면 단순 완전탐색으로 파악할 수 없으니 dp를 수행한다.
  counts = [[-1 for _ in range(A)] for _ in range(32)]
  print(getCount(30, 0, (1 << 31) - 1))

solve()