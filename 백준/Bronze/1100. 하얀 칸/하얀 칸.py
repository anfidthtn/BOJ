count = 0
for i in range(8):
    line = input()
    for j in range(8):
        if (i + j) & 1 == 0 and line[j] == 'F':
            count += 1
print(count)