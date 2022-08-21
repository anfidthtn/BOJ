import sys
n = int(input())
count = 1
for _ in range(n):
  count += int(sys.stdin.readline()) - 1
print(count)