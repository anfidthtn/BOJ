A, B = input().split()
A = list(A)
B = list(B)

A.reverse()
B.reverse()
result = [0 for _ in range(100)]

for i in range(99):
    try:
        a = int(A[i])
    except:
        a = 0
    try:
        b = int(B[i])
    except:
        b = 0
    
    result[i + 1] = int((a + b + result[i]) / 2)
    result[i] = (a + b + result[i]) % 2

if 1 not in result:
    print(0)
else:
    result.reverse()
    result = result[result.index(1):]
    for num in result:
        print(num, end='')