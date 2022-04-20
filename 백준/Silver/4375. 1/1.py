while True:
    try:
        n = int(input())
        one = 1
        count = 1
        while one % n != 0:
            count += 1
            one *= 10
            one += 1

        print(count)
    except EOFError:
        break