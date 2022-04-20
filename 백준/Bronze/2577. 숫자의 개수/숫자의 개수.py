mul_num = 1
for _ in range(3):
    temp = int(input())
    mul_num *= temp

mul_str = str(mul_num)

for i in range(10):
    print(mul_str.count('%d'%i))