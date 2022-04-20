import sys
n, q = map(int, sys.stdin.readline().split())
distanceList = {
    'Seoul' : 0.0,
    'Yeongdeungpo' : 9.1,
    'Anyang' : 23.9,
    'Suwon' : 41.5,
    'Osan' : 56.5,
    'Seojeongri' : 66.5,
    'Pyeongtaek' : 75.0,
    'Seonghwan' : 84.4,
    'Cheonan' : 96.6,
    'Sojeongni' : 107.4,
    'Jeonui' : 114.9,
    'Jochiwon' : 129.3,
    'Bugang' : 139.8,
    'Sintanjin' : 151.9,
    'Daejeon' : 166.3,
    'Okcheon' : 182.5,
    'Iwon' : 190.8,
    'Jitan' : 196.4,
    'Simcheon' : 200.8,
    'Gakgye' : 204.6,
    'Yeongdong' : 211.6,
    'Hwanggan' : 226.2,
    'Chupungnyeong' : 234.7,
    'Gimcheon' : 253.8,
    'Gumi' : 276.7,
    'Sagok' : 281.3,
    'Yangmok' : 289.5,
    'Waegwan' : 296.0,
    'Sindong' : 305.9,
    'Daegu' : 323.1,
    'Dongdaegu' : 326.3,
    'Gyeongsan' : 338.6,
    'Namseonghyeon' : 353.1,
    'Cheongdo' : 361.8,
    'Sangdong' : 372.2,
    'Miryang' : 381.6,
    'Samnangjin' : 394.1,
    'Wondong' : 403.2,
    'Mulgeum' : 412.4,
    'Hwamyeong' : 421.8,
    'Gupo' : 425.2,
    'Sasang' : 430.3,
    'Busan' : 441.7
}
station = []
data = {}
def strToMinute(strT, day):
    if strT == "-:-":
        return None
    hStr, mStr = strT.split(':')
    return 60 * int(hStr) + int(mStr) + 1440 * day
day = 0
for i in range(n):
    info = list(sys.stdin.readline().split())
    if len(data) > 0 and data[station[-1]][1] > strToMinute(info[1], day):
        day += 1
    data[info[0]] = [strToMinute(info[1], day), strToMinute(info[2], day)]
    station.append(info[0])
for i in range(q):
    start, dest = sys.stdin.readline().split()
    distance = distanceList[dest] - distanceList[start]
    timeNeed = data[dest][0] - data[start][1]
    print(abs(distance / timeNeed * 60))