import math
D, H, W = map(int, input().split())
print(int(D * H * math.sqrt((1 / (H * H + W * W)))), end=' ')
print(int(D * W * math.sqrt((1 / (H * H + W * W)))))