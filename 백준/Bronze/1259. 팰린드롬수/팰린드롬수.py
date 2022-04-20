while True:
    num = input()
    if num == '0':
        exit()
    for i in range(1 + len(num) // 2):
        if num[i] != num[- i - 1]:
            print('no')
            num = '0'
            break
    if num == '0':
        continue
    print('yes')
