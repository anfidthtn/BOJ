while True:
    order = list(map(int, input().split()))
    if order[0] | order[1] | order[2] == 0:
        exit()
    order.sort()
    if order[0] ** 2 + order[1] ** 2 == order[2] ** 2:
        print('right')
    else:
        print('wrong')
