n = int(input())
def judge(n):
    while n > 0:
        if n % 2 == 1 and n != 1:
            print(0)
            return
        n >>= 1
    print(1)

judge(n)