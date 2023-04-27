import sys
input = sys.stdin.readline
a = input().rstrip()
b = input().rstrip()
ans = [[] for _ in range(5)]
for i in range(len(a)):
    A = ord(a[i]) - ord('0')
    B = ord(b[i]) - ord('0')
    ans[0].append(A&B)
    ans[1].append(A|B)
    ans[2].append(A^B)
    ans[3].append(A^1)
    ans[4].append(B^1)
for an in ans:
    print(*an, sep='')