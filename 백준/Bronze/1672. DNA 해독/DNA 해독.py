n = int(input())
s = input()
before = s[-1]
d = {
'A' : {'A' : 'A', 'G' : 'C', 'C' : 'A', 'T' : 'G'},
'G' : {'A' : 'C', 'G' : 'G', 'C' : 'T', 'T' : 'A'},
'C' : {'A' : 'A', 'G' : 'T', 'C' : 'C', 'T' : 'G'},
'T' : {'A' : 'G', 'G' : 'A', 'C' : 'G', 'T' : 'T'}
}
for i in range(len(s) - 2, -1, -1):
  before = d[s[i]][before]

print(before)