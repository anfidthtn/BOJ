a, b = input().split()
b = int(b)
a = a.split('.')
a = int(a[0] + a[1].ljust(9, '0'))

a **= b
a = str(a).rjust(9 * b, '0')
result = []
if len(a) > 9 * b:
    result.append(a[:- 9 * b])
else:
    result.append('0')
result.append(list(a[- 9 * b:]))
while result[1][-1] == '0':
    result[1].pop()
print(int(result[0]), '.', sep='', end='')
for num in result[1]:
    print(num, end='')