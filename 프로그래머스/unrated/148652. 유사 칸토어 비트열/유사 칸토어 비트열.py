
pow4 = [4 ** i for i in range(21)]
pow5 = [5 ** i for i in range(21)]

def solution(n, l, r):
    if r < 1 or pow5[n] < l:
        # 범위 벗어났으면 0
        return 0
    if n == 0:
        # 0단계까지 내려왔으면 1
        return 1
    if l <= 1 and pow5[n] <= r:
        # 범위를 완벽히 포함하면 그 범위에 포함된 개수
        return pow4[n]
    answer = 0
    for i in range(1, 6):
        if i != 3:
            answer += solution(n - 1, l, r)
        l -= pow5[n - 1]
        r -= pow5[n - 1]
    return answer