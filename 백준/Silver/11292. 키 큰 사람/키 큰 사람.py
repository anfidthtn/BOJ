while True:
    n = int(input())
    if n == 0:
        break
    names = []
    ans = -1
    for _ in range(n):
        name, key = input().split()
        key = float(key)
        if ans < key:
            ans = key
            names = []
        if ans == key:
            names.append(name)
    print(*names)