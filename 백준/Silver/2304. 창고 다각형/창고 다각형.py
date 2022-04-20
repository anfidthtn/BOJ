n = int(input())
info = []
for _ in range(n):
	info.append(tuple(map(int, input().split())))
info.sort()

count = 0

prevIdx = 0
maxH = 0
for i in range(n):
	if maxH <= info[i][1]:
		count += maxH * (info[i][0] - prevIdx)
		prevIdx = info[i][0]
		maxH = info[i][1]

rightMaxH = 0
rightPrevIdx = info[-1][0]
for i in range(n - 1, -1, -1):
	if rightMaxH <= info[i][1]:
		count += rightMaxH * (rightPrevIdx - info[i][0])
		rightPrevIdx = info[i][0]
		rightMaxH = info[i][1]
	if rightMaxH == maxH:
		count += maxH
		break
print(count)