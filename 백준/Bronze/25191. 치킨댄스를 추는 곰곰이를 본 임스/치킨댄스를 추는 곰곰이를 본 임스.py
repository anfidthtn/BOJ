a = int(input())
b, c = map(int, input().split())
print(min(a, b // 2 + c))