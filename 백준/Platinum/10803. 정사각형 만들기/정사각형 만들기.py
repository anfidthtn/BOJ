n, m = map(int, input().split())

# 각 n, m 최소조합별 최소 개수 저장 딕셔너리
frag = {}

def getFrag(n, m):
    # n < m 이 되도록 함
    if n > m:
        n, m = m, n
    # 긴 길이가 짧은 길이로 나눠떨어지면 나눈 몫을 반환
    # ex) 2 * 6이면 2 * 2 3개로 나누면 되니 6/2 = 3 반환
    if m % n == 0:
        return int(m / n)
    # 3, 100 같은 경우 3 * 3을 31개 하고 3 * 7에 대해 계산
    # 이 때 32가 저장되는 게 count
    count = 0
    if m > n * 3:
        count = int(m / n) - 2
        m -= count * n
    # 이미 n, m조합에 대한 최소 개수가 있으면 최소 개수 + count 반환
    try:
        return frag[(n, m)] + count
    # 없으면(딕셔너리 키 에러로 except 진입) 최소 개수 구하기
    except:
        minimum = 10 ** 7
        # 짧은 길이에 대해 1 ~ 반까지 (나머지 반은 잘린 나머지부분에 대응되므로) 자른 최소 개수와 비교
        for i in range(1, int(n / 2) + 1):
            if minimum > getFrag(n - i, m) + getFrag(i, m):
                minimum = getFrag(n - i, m) + getFrag(i, m)
        # 긴 길이에 대해 1 ~ 반까지 자른 최소 개수와 비교
        for i in range(1, int(m / 2) + 1):
            if minimum > getFrag(m - i, n) + getFrag(i, n):
                minimum = getFrag(m - i, n) + getFrag(i, n)
        # 최소 개수를 저장
        frag[(n, m)] = minimum
        # 최소 개수를 리턴
        return frag[(n, m)] + count
        
print(getFrag(n, m))
# 테스트용
# for i in frag:
#     print(i, frag[i])