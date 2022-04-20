ipv6 = list(input().split(':'))
for i in range(len(ipv6)):
    if len(ipv6[i]) > 0:
        ipv6[i] = ipv6[i].rjust(4, '0')
while ipv6.count('') > 1:
    ipv6.remove('')
if ipv6.count('') == 1:
    while len(ipv6) < 8:
        ipv6.insert(ipv6.index(''), '')
        ipv6[ipv6.index('')] = '0000'
    ipv6[ipv6.index('')] = '0000'
for i in range(7):
    print(ipv6[i], end=':')
print(ipv6[i + 1])