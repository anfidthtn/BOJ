t = 0
while True:
    t += 1
    s = input()
    if s == '0':
        break
    a, b, c = map(int, s.split())
    if 4 * a * a >= b * b + c * c:
        print("Pizza ", t, " fits on the table.", sep='')
    else:
        print("Pizza ", t, " does not fit on the table.", sep='')