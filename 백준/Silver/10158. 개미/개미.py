w, h = map(int, input().split())
x, y = map(int, input().split())
t = int(input())

x += t
y += t
if (x // w) % 2 == 1:
	x = w - (x % w)
else:
	x %= w
if (y // h) % 2 == 1:
	y = h - (y % h)
else:
	y %= h
print(x, y)