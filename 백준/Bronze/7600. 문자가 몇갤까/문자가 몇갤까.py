while True:
    s = input()
    if s == "#":
        break
    s = s.lower()
    counts = [False for _ in range(1000)]
    for c in s:
        counts[ord(c)] = True
    ans = 0
    for i in range(ord('a'), ord('z') + 1):
        ans += 1 if counts[i] else 0
    print(ans)