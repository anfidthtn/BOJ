import math
import sys
sys.setrecursionlimit(10 ** 6)

n = int(input())

values = {}

def getValue(num):
    if num <= math.pi:
        return 1
    if num in values:
        return values[num]
    values[num] = (getValue(num - 1) + getValue(num - math.pi)) % 10 ** 18
    return values[num]

print(getValue(n))

# sectionEndValue = []
# for i in range(n + 1):
#     for j in range(n + 1):
#         if j == 0 and i <= 3:
#             continue
#         if i + j * math.pi <= n:
#             sectionEndValue.append(i + j * math.pi)
#         else:
#             break
# sectionEndValue.append(0.0)
# sectionEndValue.sort()

# while len(sectionEndValue) > 2 and sectionEndValue[-2] >= n:
#     sectionEndValue.pop()

# # print(sectionEndValue)
# # print(len(sectionEndValue))
# # print(sectionEndValue[-1])

# values = [1, 1]

# lastSearchIdxOne = 1
# lastSearchIdxPI = 1
# for i in range(2, len(sectionEndValue)):
#     nextValue = 0
#     while sectionEndValue[lastSearchIdxOne] < sectionEndValue[i] - 1:
#         lastSearchIdxOne += 1
#     nextValue += values[lastSearchIdxOne]
#     while sectionEndValue[lastSearchIdxPI] < sectionEndValue[i] - math.pi:
#         lastSearchIdxPI += 1
#     nextValue += values[lastSearchIdxPI]
#     values.append(nextValue % 10 ** 18)
# print(values[-1])