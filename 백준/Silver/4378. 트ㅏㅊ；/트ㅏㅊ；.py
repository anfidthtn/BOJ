line = [
    ['`', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '-', '='],
    ['Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', '[', ']', '\\'],
    ['A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', ';', '\''],
    ['Z', 'X', 'C', 'V', 'B', 'N', 'M', ',', '.', '/']
]

while True:
    try:
        sentense = list(input())
    except:
        break
    for char in sentense:
        if char == ' ':
            print(' ', end='')
            continue
        for i in range(4):
            if char in line[i]:
                print(line[i][line[i].index(char) - 1], end='')
                break
    print()