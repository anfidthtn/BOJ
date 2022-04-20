input()
An = list(map(int, input().split()))
An.sort()
input()
Mn = list(map(int, input().split()))

def search(num, left, right):
    if right < left:
        return 0
    
    if An[(left + right) >> 1] == num:
        return 1
    elif An[(left + right) >> 1] < num:
        return search(num, ((left + right) >> 1) + 1, right)
    else:
        return search(num, left, ((left + right) >> 1) - 1)

for m in Mn:
    print(search(m, 0, len(An) - 1))