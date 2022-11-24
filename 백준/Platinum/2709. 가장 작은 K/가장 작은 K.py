# def powe(num, p, mod):
#     res = 1
#     while p > 0:
#         if p % 2 == 1:
#             res *= num
#             res %= mod
#         num *= num
#         p >>= 1
#         num %= mod
#     res %= mod
#     return res

# mod = 10**20
# a = powe(2, 5**10, mod) % mod
# num = powe(2, 4503875315589, mod) % mod
# print(a, num)
# p = 4503875315589
# co = 0
# while(True):
#     check = num // 1000
#     c = 3
#     while check > 0:
#         d = check % 10
#         if d != 1 and d != 2:
#             break
#         else:
#             c += 1
#         check //= 10
#     if c >= 20:
#         print("-----")
#         print(num, c, p)
#         print("-----")
#         break
#     p += 5**10
#     num *= a
#     num %= mod
#     co += 1
#     if co % 10000 == 0:
#         print(p, end=' ')
        
        
        

ans = [0, 1, 9, 89, 89, 589, 3089, 3089, 3089, 315589, 315589, 8128089, 164378089, 945628089, 1922190589, 11687815589, 109344065589, 231414378089, 1452117503089, 4503875315589, 65539031565589]

t = int(input())
for _ in range(t):
    print(ans[int(input())])