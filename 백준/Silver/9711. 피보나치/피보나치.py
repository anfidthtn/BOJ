t = int(input())
fib = [0, 1, 1, 2]
for i in range(4, 10001):
    fib.append(fib[-1] + fib[-2])
for ttttt in range(1, t + 1):
    p, q = map(int, input().split())
    print("Case #",ttttt,": ",fib[p]%q,sep='')