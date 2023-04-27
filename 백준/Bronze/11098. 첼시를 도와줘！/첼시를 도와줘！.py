t = int(input())
for _ in range(t):
    p = int(input())
    low = -1
    lowname = ""
    for _ in range(p):
        price, name = input().split()
        price = int(price)
        if price > low:
            low = price
            lowname = name
    print(lowname)