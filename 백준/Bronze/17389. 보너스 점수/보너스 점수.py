n = int(input())
result = "0" + input()
st = 0
ans = 0
for i in range(1, n + 1):
    if result[i] == 'O':
        ans += st + i
        st += 1
    else:
        st = 0
print(ans)