def solve():
    s = input()
    for i in range(len(s)):
        if s[i] != s[len(s) - i - 1]:
            print(0)
            return
    print(1)

    
solve()