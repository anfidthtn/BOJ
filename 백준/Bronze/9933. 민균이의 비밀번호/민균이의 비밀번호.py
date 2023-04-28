n = int(input())
s = set()
for _ in range(n):
    ss = input()
    s.add(ss)
    if ss[::-1] in s:
        print(len(ss), ss[len(ss) // 2], sep=' ')
        break