n = int(input())


def judge(x):
    if str(x * x)[-len(str(x)):] == str(x):
        return "YES"
    return "NO"

for _ in range(n):
    print(judge(int(input())))