import math
n=int(input())
k = 10 ** n
n = 5 ** n
n = str(n).rjust(len(str(k)), '0')
print(0,n[1:],sep='.')