H,M,S = map(int, input().split(':'))
A = H * 3600 + M * 60 + S
H,M,S = map(int, input().split(':'))
B = H * 3600 + M * 60 + S
B += 86400
B -= A
B %= 86400
if B == 0:
    B = 86400
print(str(B // 3600).rjust(2, '0'), str((B % 3600) // 60).rjust(2, '0'), str(B % 60).rjust(2, '0'), sep=':')