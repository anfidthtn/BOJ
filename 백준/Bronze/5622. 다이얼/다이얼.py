word = input()

alpha = {}
alpha['a'] = 3
alpha['b'] = 3
alpha['c'] = 3
alpha['d'] = 4
alpha['e'] = 4
alpha['f'] = 4
alpha['g'] = 5
alpha['h'] = 5
alpha['i'] = 5
alpha['j'] = 6
alpha['k'] = 6
alpha['l'] = 6
alpha['m'] = 7
alpha['n'] = 7
alpha['o'] = 7
alpha['p'] = 8
alpha['q'] = 8
alpha['r'] = 8
alpha['s'] = 8
alpha['t'] = 9
alpha['u'] = 9
alpha['v'] = 9
alpha['w'] = 10
alpha['x'] = 10
alpha['y'] = 10
alpha['z'] = 10

time = 0
for i in range(len(word)):
    time += alpha[word[i].lower()]

print(time)

