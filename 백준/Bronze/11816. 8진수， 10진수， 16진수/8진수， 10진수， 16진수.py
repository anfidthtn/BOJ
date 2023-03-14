n=input()
a=0
if n[0] != '0':
    for i in range(len(n)):
        a*=10
        a+=ord(n[i]) - ord('0')
elif n[1] != 'x':
    for i in range(1, len(n)):
        a *=8
        a+=ord(n[i]) - ord('0')
else:
    for i in range(2, len(n)):
        a *= 16
        if ord('a') <= ord(n[i]) <= ord('z'):
            a += ord(n[i]) - ord('a') + 10
        else:
            a += ord(n[i]) - ord('0')
print(a)