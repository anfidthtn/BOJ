d = {}
for i in range(1, 27):
    d[chr(ord('a') + i - 1)] = i
for i in range(27, 53):
    d[chr(ord('A') + i - 27)] = i

ans = 0
for c in input():
    ans += d[c]

def judge(x):
    for i in range(2, x):
        if x % i == 0:
            return False
    return True

print("It is", "" if judge(ans) else " not", " a prime word.", sep='')