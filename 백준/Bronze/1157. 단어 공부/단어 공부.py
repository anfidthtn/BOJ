sen = input()
sen = sen.upper()

maxalpha = '0'
maxcount = 0
isValid = True

for i in range(26):
    nowCount = sen.count(chr(ord('A') + i))
    if nowCount > maxcount:
        maxalpha = chr(ord('A') + i)
        maxcount = nowCount
        isValid = True
    elif nowCount == maxcount:
        isValid = False

if isValid is False:
    print('?')
else:
    print(maxalpha)