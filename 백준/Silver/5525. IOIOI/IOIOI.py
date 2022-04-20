n = int(input())
m = int(input())
s = list(input())

searchIdx = 0
count = 0

# for i in range(m):
#     print(i, s[i], end='  ')
#     if (i + 1) % 10 == 0:
#         print()

while searchIdx < m - 2 * n:
    if s[searchIdx] == 'I':
        isCorrect = True
        for i in range(n + 1):
            if i % 2 == 0:
                check = 'I'
            else:
                check = 'O'
            if s[searchIdx + i] != check or s[searchIdx + 2 * n - i] != check:
                isCorrect = False
                if i == 0:
                    searchIdx += 1
                elif check == 'O': # 끝부분이 IIOIOI IIOI II 같은식으로 끝나는 상황 or  II - OI II - II 
                    if s[searchIdx + 2 * n - i] == 'I':
                        searchIdx += 2 * n - i + 1
                    else:
                        searchIdx += i + 1
                else: # check == 'I'
                    if s[searchIdx + 2 * n - i] == 'O': # 끝부분이 'O'OI가 되는 상황
                        searchIdx += 2 * n - i + 1
                    else: # s[searchIdx + i] == 'O':
                        searchIdx += i + 1
                break
        if isCorrect is False:
            continue
        count += 1
        searchIdx = searchIdx + 2 * n + 2
        while searchIdx < m:
            if s[searchIdx - 1] == 'I':
                searchIdx -= 1
                break
            if s[searchIdx] == 'I':
                count += 1
                searchIdx += 2
            else:
                searchIdx += 1
                break
    else:
        searchIdx += 1

print(count)