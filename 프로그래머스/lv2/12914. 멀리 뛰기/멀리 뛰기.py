def solution(n):
    res = [1, 1]
    while len(res) <= n:
        res.append((res[-1] + res[-2]) % 1234567)
    return res[n]