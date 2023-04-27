import sys
input = sys.stdin.readline
n = int(input())
for _ in range(n):
    A = input()
    B = input()
    ans = 0
    for i in range(len(A)):
        ans += (ord(A[i]) - ord('0')) ^ (ord(B[i]) - ord('0'))
    print("Hamming distance is ", ans, ".", sep='')