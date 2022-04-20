H, W = map(int, input().split())

heights = list(map(int, input().split()))
HEIGHT = 'HEIGHT'
STARTIDX = 'STARTIDX'
DIFF = 'DIFF'
# 현재 저장된 높이, 인덱스(최저높이의 시작지점), 전까지 최고높이와의 차이
# 를 스택으로
HLsit = [{HEIGHT : heights[0], STARTIDX : 0, DIFF : 0}]

sum = 0
for idx, height in enumerate(heights[1:]):
    idx += 1 # 1번째 원소 빼버려서 인덱스 +1
    # 최저높이랑 같은 높이면 패스
    if height == HLsit[-1][HEIGHT]:
        continue
    # 최저높이 갱신
    elif height < HLsit[-1][HEIGHT]:
        HLsit.append({HEIGHT : height, STARTIDX : idx, DIFF : HLsit[-1][HEIGHT] - height})
    # 최저높이보다 높은거 들어오면 받아지는 빗물 양 계산하고, 해당 위치를 다시 현 높이로 채운 상태로 만듦
    # ex) 3 1 1 2 -> 2만큼 받고 3 2 2 2로 만듦
    # ex) 3 1 1 4 -> 4만큼 받고 4만 남김
    else:
        tempIdx = -1
        while len(HLsit) > 0 and height > HLsit[-1][HEIGHT]:
            if height <= HLsit[-1][HEIGHT] + HLsit[-1][DIFF]:
                sum += (idx - HLsit[-1][STARTIDX]) * (height - HLsit[-1][HEIGHT])
            else:
                sum += (idx - HLsit[-1][STARTIDX]) * HLsit[-1][DIFF]
            tempIdx = HLsit[-1][STARTIDX]
            HLsit.pop()
        if len(HLsit) > 0 and height == HLsit[-1][HEIGHT]:
            continue
        if len(HLsit) > 0:
            HLsit.append({HEIGHT : height, STARTIDX : tempIdx, DIFF : HLsit[-1][HEIGHT] - height})
        else:
            HLsit.append({HEIGHT : height, STARTIDX : idx, DIFF : 0})
print(sum)