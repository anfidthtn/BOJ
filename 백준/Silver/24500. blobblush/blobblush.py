n = int(input())
k = n ^ ((1 << (len(bin(n)) - 2)) - 1)
if k > 0:
    print(2)
    print(k)
    print(n)
else:
    print(1)
    print(n)
