import math
x = int(input())
'''
n(n + 1) / 2 = x
(n + 1/2) ^ 2 = 2x + 1/4
n + 1/2 = (2x + 1/4) ** 0.5
n = (2x + 1/4) ** 0.5 - 1/2
'''
n = math.ceil((2 * x + 0.25) ** 0.5 - 0.5)
diff = n * (n + 1) // 2 - x # 몇 번 움직여야하는지
if n % 2 == 0: # 짝수 번째 대각선인 경우, n행 1열에서 끝남.(인덱스 넘버가 n, 1이 아니고 진짜 n행 1열)
    row = n
    col = 1
    row -= diff
    col += diff
else:
    row = 1
    col = n
    row += diff
    col -= diff
print(row, '/', col, sep='')
