n = int(input())
result = ['L', 'W', 'L', 'W', 'W', 'W', 'W']
n %= 7
if result[n] == 'L':
    print('CY')
else:
    print('SK')