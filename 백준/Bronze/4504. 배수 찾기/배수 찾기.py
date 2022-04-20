import sys
n = int(input())
while True:
    num = int(sys.stdin.readline())
    if num == 0:
        break
    if num % n == 0:
        print('%d is a multiple of %d.' % (num, n))
    else:
        print('%d is NOT a multiple of %d.' % (num, n))