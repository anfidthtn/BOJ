n = int(input()) // 100 * 100
f = int(input())
for i in range(f):
    if (n + i) % f == 0:
        print("%02d" % i)
        break