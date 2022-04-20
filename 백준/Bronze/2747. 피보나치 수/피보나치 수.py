fib = [0, 1]
n = int(input())
while len(fib) <= n:
    fib.append(fib[-2] + fib[-1])
print(fib[-1])