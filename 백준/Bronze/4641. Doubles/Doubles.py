while True:
    s = input()
    if s == "-1":
        break
    nums = list(map(int, s.split()))
    ans = -1
    for num in nums:
        if num * 2 in nums:
            ans += 1
    print(ans)