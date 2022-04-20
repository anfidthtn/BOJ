n, k = map(int, input().split())
An = list(map(int, input().split()))

def GCD(a, b = None):
    if b is None:
        return a
    while b > 0:
        temp = a % b
        a = b
        b = temp
    return a

gcdDict = {} # k과의 최대공약수별 개수 셈

for i in range(n):
    An[i] = GCD(k, An[i])
    try:
        gcdDict[An[i]] += 1
    except:
        gcdDict[An[i]] = 1

count = 0
sortedGCDs = list(gcdDict.keys())
sortedGCDs.sort()
for p in range(len(sortedGCDs)):
    gcdP = sortedGCDs[p]
    if (gcdP ** 3) % k == 0:
        # 같은 최대공약수 3개의 조합으로 k배수 만족하는 경우
        if gcdDict[gcdP] >= 3:
            # 3개 이상 있을 때만 성립해야하니까 이 때 더해줌
            # 더하는 값은 gcdDict[gcdP] 개 중 3개를 고르는 조합
            count += (gcdDict[gcdP] - 2) * (gcdDict[gcdP] - 1) * gcdDict[gcdP] // 6
    for q in range(p + 1, len(sortedGCDs)):
        gcdQ = sortedGCDs[q]
        if (gcdP ** 2 * gcdQ ** 1) % k == 0:
            # 서로 다른 2개의 최대공약수 2개 / 1개 조합
            if gcdDict[gcdP] >= 2 and gcdDict[gcdQ] >= 1:
                # 마찬가지로 개수가 맞아야 더해줄 수 있음
                # gcdDict[gcdP] 개 중 2개, gcdDict[gcdQ] 개 중 1개
                count += ((gcdDict[gcdP] - 1) * gcdDict[gcdP] // 2) * gcdDict[gcdQ]
        if (gcdP ** 1 * gcdQ ** 2) % k == 0:
            # 서로 다른 2개의 최대공약수 1개 / 2개 조합
            if gcdDict[gcdP] >= 1 and gcdDict[gcdQ] >= 2:
                # 마찬가지로 개수가 맞아야 더해줄 수 있음
                # gcdDict[gcdP] 개 중 1개, gcdDict[gcdQ] 개 중 2개
                count += gcdDict[gcdP] * ((gcdDict[gcdQ] - 1) * gcdDict[gcdQ] // 2)
        for r in range(q + 1, len(sortedGCDs)):
            gcdR = sortedGCDs[r]
            if gcdP == gcdR or gcdQ == gcdR: # p == q는 위에서 검사해서 안해도 됨
                continue
            if (gcdP ** 1 * gcdQ ** 1 * gcdR ** 1) % k == 0:
                # 서로 다른 3개의 최대공약수 1개 / 1개 / 1개 조합
                if gcdDict[gcdP] >= 1 and gcdDict[gcdQ] >= 1 and gcdDict[gcdR] >= 1:
                    # 마찬가지로 개수 맞아야 더함
                    count += gcdDict[gcdP] * gcdDict[gcdQ] * gcdDict[gcdR]
print(count)






# 첨에 했다가 시간초과 받은 코드 (받을 줄 알고는 있었음)
# 최초에는 An을 미리 gcd화 한 다음 곱이 >= k 되는 순간 early stop하려했으나 (물론 이것도 맞다 쳐도 시간초과는 뜸) 틀렸다는거 깨닫고
# 그냥완전탐색때려벌임 ㅎ
# count = 0
# for p in range(n):
#     if An[p] % k == 0:
#         count += ((n - p - 2) * (n - p - 1)) // 2
#         continue
#     for q in range(p + 1, n):
#         if (An[p] * An[q]) % k == 0:
#             count += n - q - 1
#             continue
#         for r in range(q + 1, n):
#             if (An[p] * An[q] * An[r]) % k == 0:
#                 count += 1

# print(count)
