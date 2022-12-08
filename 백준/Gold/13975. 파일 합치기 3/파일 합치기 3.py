import sys
import heapq

for _ in range(int(input())):
    k = sys.stdin.readline()

    # 서로 같은 값이 들어올 확률이 더 많아서, 값의 개수를 카운팅하는식으로 결정
    valuesCount = {}
    values = list(map(int, sys.stdin.readline().split()))

    # valuesCount에 있는 값을 최소값부터 순서대로 넣어둔 것
    heap = []
    heapq.heapify(heap)
    for value in values:
        if value == 0:
            # 0장짜리는 할 필요가 없다.
            continue
        if value in valuesCount:
            # 이미 나온 값이면 나온 횟수 더해주기
            valuesCount[value] += 1
        else:
            # 안 나온 값이면 나온 횟수 1로 설정하며, 새로운 값이 나온 것을 heap에 등록
            valuesCount[value] = 1
            heapq.heappush(heap, value)
    
    totalSum = 0
    while len(heap) > 1 or ( len(heap) == 1 and valuesCount[heap[0]] > 1):
        # 남은 값의 개수가 2개 이상이면 돌림
        minValueCount = valuesCount[heap[0]]
        if minValueCount > 1:
            # 2개 이상이 있으면 heap[0] 크기를 가진 애끼리 합친다.
            totalSum += heap[0] * 2 * (minValueCount >> 1)
            if heap[0] * 2 not in valuesCount:
                valuesCount[heap[0] * 2] = 0
                heapq.heappush(heap, heap[0] * 2)
            valuesCount[heap[0] * 2] += (minValueCount >> 1)

            # 홀수냐 짝수냐에 따라 처리한다.
            if minValueCount & 1 == 1:
                valuesCount[heap[0]] = 1
            else:
                # 짝수였다면 0개 남으니 없앤다.
                del valuesCount[heap[0]]
                # 최저값이 없어졌으니 heap에서 1개를 팝 한다.
                heapq.heappop(heap)
        else:
            # 1개 남아있으면 뒤에 애랑 같이 본다.
            # 카운트 0되니까 삭제 먼저
            del valuesCount[heap[0]]
            # 값 꺼내오고
            minValue1 = heapq.heappop(heap)
            # 값 읽어오고
            minValue2 = heap[0]
            # 일단 합친 것에 대한 처리
            totalSum += minValue1 + minValue2
            if minValue1 + minValue2 not in valuesCount:
                valuesCount[minValue1 + minValue2] = 0
                heapq.heappush(heap, minValue1 + minValue2)
            valuesCount[minValue1 + minValue2] += 1

            if valuesCount[minValue2] > 1:
                # 새로운 최저값이 2개 이상 있으면 그냥 둘이 합친 것으로 치면 됨
                valuesCount[minValue2] -= 1
            else:
                # 새로운 최저값도 1개면 얘도 valuesCount 없애는 등의 작업 해야함
                del valuesCount[minValue2]
                heapq.heappop(heap)
    print(totalSum)