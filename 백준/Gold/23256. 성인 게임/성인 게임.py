import sys

ways1 = [3]
ways2 = [7]

T = int(input())
for _ in range(T):
    N = int(sys.stdin.readline())
    for i in range(len(ways1), N):
        ways1.append((ways1[i - 1] * 2 + ways2[i - 1])  % 1000000007)
        ways2.append((ways1[i - 1] * 4 + ways2[i - 1] * 3) % 1000000007)
    print(ways2[N - 1])