s = input()
def solve():
    left = 0
    right = len(s) - 1
    while left < right:
        if s[left] != s[right]:
            return "false"
        left += 1
        right -= 1
    return "true"
print(solve())