for a in range(2, 101):
    for b in range(2, 101):
        if 3 * b ** 3 > a ** 3:
            break
        for c in range(b, 101):
            if b ** 3 + 2 * c ** 3 > a ** 3:
                break
            for d in range(c, 101):
                if b ** 3 + c ** 3 + d ** 3 > a ** 3:
                    break
                if b ** 3 + c ** 3 + d ** 3 == a ** 3:
                    print('Cube = %d, Triple = (%d,%d,%d)' % (a, b, c, d))