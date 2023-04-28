import sys
input = sys.stdin.readline
n = int(input())
for _ in range(n):
    s = input().split()
    a = ord(s[0][0]) - ord('A') + 1 + (ord(s[0][1]) - ord('0') - 1)
    b = int(s[1]) + (int(s[1]) - 1) // 8
    print("YES" if ((a + b) & 1)^1 else "NO")