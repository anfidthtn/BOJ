def solution(n):
    fibb = [0, 1, 1]
    while len(fibb) <= n:
        fibb.append((fibb[-1] + fibb[-2]) % 1234567)
    return fibb[n]