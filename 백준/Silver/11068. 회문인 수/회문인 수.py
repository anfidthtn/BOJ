n = int(input())

def check(x):
    for b in range(2, 65):
        st = []
        n = x
        while n > 0:
            st.append(n % b)
            n //= b
        left = 0
        right = len(st) - 1
        result = True
        while left < right:
            if st[left] != st[right]:
                result = False
                break
            left += 1
            right -= 1
        if result:
            return 1
    return 0

for _ in range(n):
    print(check(int(input())))