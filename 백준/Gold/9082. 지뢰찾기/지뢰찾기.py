t = int(input())

def fillMines(nums, mines):
    for i in range(1, n - 1):
        need = int(nums[i])
        if mines[i - 1] == '*':
            need -= 1
        if mines[i] == '*':
            need -= 1
        if need == 1:
            mines[i + 1] = '*'
        elif need == 0:
            if mines[i + 1] == '*':
                return -1
            mines[i + 1] = ' '
        else:
            return -1
    need = int(nums[n - 1])
    if mines[n - 2] == '*':
        need -= 1
    if mines[n - 1] == '*':
        need -= 1
    if need != 0:
        return -1
    return mines.count('*')

for _ in range(t):
    n = int(input())
    nums = list(input())
    mines = list(input())
    if n <= 2:
        print(nums[0])
        continue
    if int(nums[0]) == 0:
        mines[0] = ' '
        mines[1] = ' '
    if int(nums[0]) == 2:
        mines[0] = '*'
        mines[1] = '*'
    if int(nums[0]) & 1 == 0:
        print(fillMines(nums, mines))
        continue
    if mines[0] == '*':
        mines[1] == ' '
        print(fillMines(nums, mines))
        continue
    if mines[1] == '*':
        mines[0] == ' '
        print(fillMines(nums, mines))
        continue
    mines[0] = ' '
    mines[1] = '*'
    a = fillMines(nums.copy(), mines.copy())
    mines[0] = '*'
    mines[1] = ' '
    b = fillMines(nums, mines)
    print(max(a, b))