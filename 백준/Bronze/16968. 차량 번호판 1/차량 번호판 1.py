s = input()
before = 'a'
cases = 1
for c in s:
    if c == 'd':
        cases *= 10 if c != before else 9
    else:
        cases *= 26 if c != before else 25
    before = c
print(cases)