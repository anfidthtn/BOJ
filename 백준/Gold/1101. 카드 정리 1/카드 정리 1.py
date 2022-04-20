n, m = map(int, input().split())
maxCount = 0
dataList = [] # 각 입력행별로 색상이 있는지 없는지만 이진법으로 넣음

for _ in range(n):
    now = list(input().split())
    data = 0
    point = 1
    for color in now:
        if color != '0':
            data += point
        point <<= 1
    if data > 0:
        dataList.append(data)


for i in range(m):
    if 1 << i in dataList:
        dataList.remove(1 << i)

if len(dataList) > 0:
    print(len(dataList) - 1)
else:
    print(0)