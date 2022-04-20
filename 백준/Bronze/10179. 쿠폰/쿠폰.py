import sys
t = int(input())
for _ in range(t):
  print("$%.2f" % (round(float(input()) * 0.8, 2)))