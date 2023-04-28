while True:
    s = input()
    if s[0] == '0':
        break
    nums = list(map(int, s.split()))
    s = nums[0]
    nums = nums[1:]
    before = 0
    for num in nums:
        if num == before:
            continue
        before = num
        print(num, end=' ')
    print('$')