def check(x):
    left = 0
    right = len(x) - 1
    while left < right:
        if x[left] != x[right]:
            return "No"
        left += 1
        right -= 1
    return "Yes"

n = int(input())

for _ in range(n):
    print(check(input().lower()))