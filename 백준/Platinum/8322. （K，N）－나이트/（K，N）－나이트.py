import sys

def GCD(a, b = None):
    if b is None:
        return a
    while b > 0:
        temp = a % b
        a = b
        b = temp
    return a

t = int(sys.stdin.readline())
for _ in range(t):
    k, n, x1, y1, x2, y2 = map(int, sys.stdin.readline().split())
    if x1 == x2 and y1 == y2: # 시작점과 도착점이 같으면 무조건 됨
        print('TAK')
        continue
    xDistance = x1 - x2 if x1 > x2 else x2 - x1
    yDistance = y1 - y2 if y1 > y2 else y2 - y1
    if k == 0 or n == 0: # 최소이동 단위(최대공약수)를 구함
        if k == 0 and n == 0: # 둘 다 0이면 시작점 == 도착점(for문 시작할 때 가능처리) 말곤 안되니까 불가능
            print('NIE')
            continue
        distanceUnit = k | n # 한 쪽만 0이면 다른 한 쪽이 최소이동단위
    else:
        distanceUnit = GCD(k, n) # 두 쪽 다 0이 아니니 둘의 최대공약수가 그 뭐냐 최소이동단위
    if distanceUnit > 1: # 최소이동단위가 2 이상일 땐
        '''
        2라고 치면
        ■□■□■□■□■□■□
        □□□□□□□□□□□□
        ■□■□■□■□■□■□
        □□□□□□□□□□□□
        ■□■□■□■□■□■□
        □□□□□□□□□□□□
        ■□■□■□■□■□■□
        □□□□□□□□□□□□
        여기에서 빈 사각형에는 못감
        '''
        if xDistance % distanceUnit != 0 or yDistance % distanceUnit != 0: # 두 방향 중 가야할 거리가 최소이동단위랑 맞지 않으면 이동불가
            print('NIE')
            continue
        # 최소이동단위를 이용해 재편성
        xDistance //= distanceUnit
        yDistance //= distanceUnit
        k //= distanceUnit
        n //= distanceUnit
    if k % 2 == 0 or n % 2 == 0: # 서로소 관계의 두 수 중 짝수가 있으면 대각(+-1, +-1), 수직(+-1, 0), 수평(0, +-1) 이동까지 전부 이동 가능 가능
        print('TAK')
        continue
    else: # 짝수가 없으면 대각이동(+-1, +-1)만 가능
        if (xDistance ^ yDistance) & 1 == 1: # 목표지점이 수직수평방향 이동 필요한 곳이면 이동불가
            print('NIE')
            continue
        else: # 목표지점이 대각방향이동으로 갈 수 있으면 가능
            print('TAK')
            continue
    '''
    바로 위에꺼처럼 되는 이유
    기본적으로 (0, 0)에서 출발하여 (홀수, 홀수)를 더하면 (홀수, 홀수) 혹은 (짝수, 짝수)만 나오지 (홀수, 짝수) 나 (짝수, 홀수)는 나올 수 없음.
    이 부분이 (홀수, 홀수) 서로소에서 대각만 본 이유임
    
    또
    □□□□□□□□□□□□
    □A□□□□□□□□□□
    □□□□□□C□B□□□
    □□□□□□□□□□□□
    A 에서 한 방향으로 1칸차이나는 B로 이동가능하다면(세로 1차이)
    가로 -1 세로 Y만큼 이동, 가로 -1 세로 -Y만큼 이동해서 C로 이동가능
    
    그리고 홀수 서로소 쌍 a, b에 대해
    (0, 0) -> (a, b) --(-b, -a)-> (a - b, b - a) --(+b, -a)-> (a, b - 2a) 이런식으로 이동가능한 좌표의 절댓값을 최종적으로 1로 만들 수 있음
    (절댓값이) 1까지 가는건 최저값이 3이상이라 가정하면 모순나와서 증명가능함
    1까지 갔단 소리는 대각선으로 이동이 가능하다는 소리

    마찬가지로 홀수, 짝수 서로소쌍 a, b에 대해서도 기본적으로 위의 방법으로 1까지 갈 수 있으므로 대각이동이 가능하고
    (0, 0) -> (a, b)로 이동하면 바로 옆의 대각원소들로도 이동할 수 있으므로 최종적으로 상하좌우 이동도 가능하다는 소리라서
    모든 곳으로 이동이 된다.
    '''