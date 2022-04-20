t = int(input())
def change(num):
    if num <= 0:
        return []
    return change(num // 9) + [num % 9]
result = change(t)
if len(result) == 0:
    result = [0]

for i in result:
    print(i, end='')