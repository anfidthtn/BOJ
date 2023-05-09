def solution(a, b, n):
    answer = 0
    while n >= a:
        temp = n // a * b
        answer += temp
        n %= a
        n += temp
    return answer