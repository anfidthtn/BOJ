n=int(input())
d={}
for _ in range(n):
    s = input()
    if s not in d:
        d[s] =0
    d[s] += 1

target = max(d.values())
for key in sorted(d.keys()):
    if d[key] == target:
        print(key)
        break