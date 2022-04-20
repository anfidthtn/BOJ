from collections import deque

N, M = map(int, input().split())

Map = [['#' for _ in range(M + 2)] for _ in range(N + 2)]
Turn = 0
direction = {'U' : (-1, 0), 'R' : (0, 1), 'D' : (1, 0), 'L' : (0, -1)}
player = None

def max(a, b):
    if a > b:
        return a
    return b

def min(a, b):
    if a > b:
        return b
    return a

class ItemBox:
    def __init__(self, ItemType, ItemValue):
        self.ItemType = ItemType
        self.ItemValue = ItemValue
    
class Monster:
    def __init__(self, row, col, name, weapon, armor, hp, exp):
        self.row = row
        self.col = col
        self.name = name
        self.weapon = weapon
        self.armor = armor
        self.originHp = hp  # 플레이어가 리인카를 갖고 죽었을 때 회복시키는 용도
        self.hp = hp
        self.exp = exp
        if Map[row][col] == 'M':
            self.isBoss = True
        else:
            self.isBoss = False
    
    def heal(self): # 플레이어가 리인카를 갖고 죽었을 때 회복시키는 용도
        self.hp = self.originHp
    
    def hitDamage(self, damage):
        self.hp -= damage

class Player:
    maxHp = 20
    hp = 20
    weapon = 2
    armor = 2
    level = 1
    exp = 0

    equip_weapon = 0
    equip_armor = 0
    equip_accCount = 0

    isEquip_HR = False
    isEquip_RE = False
    isEquip_CO = False
    isEquip_EX = False
    isEquip_DX = False
    isEquip_HU = False
    isEquip_CU = False

    def __init__(self, row, col):
        self.startingLocation = [row, col]
        self.nowLocation = [row, col]
    
    def Move(self, dir):
        # 다음 행선지가 벽인지 아닌지에 따라 이동 or 제자리(제자리 이동)
        if Map[self.nowLocation[0] + direction[dir][0]][self.nowLocation[1] + direction[dir][1]] != '#' :
            self.nowLocation[0] += direction[dir][0]
            self.nowLocation[1] += direction[dir][1]
        # 이동 후 충돌하는 오브젝트에 대한 처리
        mapObject = Map[self.nowLocation[0]][self.nowLocation[1]]
        # 몬스터면 배틀!
        if type(mapObject) is Monster:
            Battle(self, mapObject)
        # 아이템이면 상자깡!
        elif type(mapObject) is ItemBox:
            self.earnItemBox()
        # 가시면 오마이갓!
        elif mapObject == '^':
            self.hitSpikeTrap()
    
    def heal(self):
        self.hp = self.maxHp

    def earnExp(self, exp):
        if self.isEquip_EX is True: # 장신구 갖고있으면 1.2배 오르고
            self.exp += int(exp * 1.2)
        else: # 아니면 그대로
            self.exp += exp

        if self.exp >= self.level * 5: # 레벨 * 5 넘기면 레벨업
            self.level += 1 # 레벨 오르고
            self.exp = 0 # 경험치 0되고
            self.maxHp += 5 # 맥스 5 오르고
            self.heal() # 최대치 회복되고
            self.weapon += 2 # 공 2 오르고
            self.armor += 2 # 방 2 오르고
    
    def hitDamage(self, damage):
        self.hp -= damage # 처맞고
        
        if self.hp <= 0: # hp 0되면 일단은 끝나야하는데
            # 해당 위치의 오브젝트가 뭔지 파악 (죽은 원인 서술해야함)
            mapObject = Map[self.nowLocation[0]][self.nowLocation[1]]
            if self.isEquip_RE is True: # 리인카 터지면 일단 살아
                self.isEquip_RE = False # 터졌으니 장신구 ㅂㅂ
                self.heal()
                self.equip_accCount -= 1 # 장신구 개수 줄이기
                self.nowLocation = self.startingLocation.copy() # 첫 시작위치로 ㄱㄱ
                return True # 리인카 터졌다는 소리 (몬스터 회복은 Battle 클래스에서 담당)
            else: # 리인카 안 터지면 게임 끝
                printMap()
                self.printInfo() # 현재 상태 출력
                if type(mapObject) is Monster:
                    print("YOU HAVE BEEN KILLED BY ", mapObject.name, "..", sep='')
                    exit(0)
                elif mapObject == '^':
                    print("YOU HAVE BEEN KILLED BY SPIKE TRAP..")
                    exit(0)
                else:
                    print("여기오는 코드 있음? 내가 문제 조건에서 못 본 걸수도")
                    Map[10000] = 100 # 고의로 인덱스 에러내기
        return False # 플레이어 안 뒤져서 리인카 안 터짐
    
    def hitSpikeTrap(self):
        if self.isEquip_DX is True:
            self.hitDamage(1)
        else:
            self.hitDamage(5)
    
    def earnItemBox(self):
        mapObject = Map[self.nowLocation[0]][self.nowLocation[1]]
        if mapObject.ItemType == 'W':
            self.equip_weapon = mapObject.ItemValue
        elif mapObject.ItemType == 'A':
            self.equip_armor = mapObject.ItemValue
        elif self.equip_accCount < 4:
            accType = mapObject.ItemValue
            if accType == 'HR':
                if self.isEquip_HR is False:
                    self.equip_accCount += 1
                    self.isEquip_HR = True
            if accType == 'RE':
                if self.isEquip_RE is False:
                    self.equip_accCount += 1
                    self.isEquip_RE = True
            if accType == 'CO':
                if self.isEquip_CO is False:
                    self.equip_accCount += 1
                    self.isEquip_CO = True
            if accType == 'EX':
                if self.isEquip_EX is False:
                    self.equip_accCount += 1
                    self.isEquip_EX = True
            if accType == 'DX':
                if self.isEquip_DX is False:
                    self.equip_accCount += 1
                    self.isEquip_DX = True
            if accType == 'HU':
                if self.isEquip_HU is False:
                    self.equip_accCount += 1
                    self.isEquip_HU = True
            if accType == 'CU':
                if self.isEquip_CU is False:
                    self.equip_accCount += 1
                    self.isEquip_CU = True
        Map[self.nowLocation[0]][self.nowLocation[1]] = '.'
    
    def printInfo(self):
        print("Passed Turns : ", Turn, sep='')
        print("LV : ", self.level, sep='')
        if self.hp <= 0:
            print("HP : ", 0, "/", self.maxHp, sep='')
        else:
            print("HP : ", self.hp, "/", self.maxHp, sep='')
        print("ATT : ", self.weapon, '+', self.equip_weapon, sep='')
        print("DEF : ", self.armor, '+', self.equip_armor, sep='')
        print("EXP : ", self.exp, '/', self.level * 5, sep='')


class Battle:
    def __init__(self, player, monster):
        self.player = player
        self.monster = monster
        if self.monster.isBoss is True: # 상대 몬스터가 보스면
            self.bossFirstAttack = True # 보스의 첫 공격이다. 를 활성화 (딜 0넣어야해서)
            if self.player.isEquip_HU is True: # 헌터 장비 끼고있으면
                self.player.heal() # 보스랑 만났으니 체력회복함
        self.playerFirstAttack = True # 플레이어 선빵 활성화 (딜 배수로 넣기)

        # 플레이어부터 턴 시작
        self.playerAttack()

    def playerAttack(self):
        if self.playerFirstAttack is True: # 첫 공격 한정
            self.playerFirstAttack = False # 다음부터 첫 공격 아닌걸로 ㅎㅎ
            if self.player.isEquip_CO is True: # CO만 끼고있으면 선빵 2배
                if self.player.isEquip_DX is True: # DX도 같이끼고 있으면 선빵 3배
                    self.monster.hitDamage(max(1, (self.player.weapon + self.player.equip_weapon) * 3 - self.monster.armor))
                else: # CO만 낀 경우 2배처리
                    self.monster.hitDamage(max(1, (self.player.weapon + self.player.equip_weapon) * 2 - self.monster.armor))
            else: # CO없는경우 1배처리
                self.monster.hitDamage(max(1, self.player.weapon + self.player.equip_weapon - self.monster.armor))
        else: # 기본 1배처리
            self.monster.hitDamage(max(1, self.player.weapon + self.player.equip_weapon - self.monster.armor))
        
        # 몬스터는 배틀때만 죽을 수 있고 데미지도 여러 분야로 처맞아서 배틀 때 죽는 처리하는 게 나음

        if self.monster.hp <= 0:
            # 몬스터가 죽으면 일단 빈 자리로 만들자
            Map[self.monster.row][self.monster.col] = '.'
            # 몬스터가 죽으면 일단 경험치를 주자
            self.player.earnExp(self.monster.exp)
            # 아 ㅋㅋㅋㅋㅋㅋㅋㅋㅋ HR을 아예 안넣었었어 ㅋㅋㅋㅋㅋ
            if self.player.isEquip_HR is True:
                self.player.hp = min(self.player.hp + 3, self.player.maxHp)
            if self.monster.isBoss is True: # 보스인지 판정
                # 보스 잡으면 끝
                printMap()
                self.player.printInfo()
                print('YOU WIN!')
                exit(0)
            else:
                # 일반몬스터는 잡으면 그냥 끝
                return

        # 몬스터가 안 죽으면 몬스터 공격차례
        self.monsterAttack()


    def monsterAttack(self):
        # 보스일 때 템 있으면 첫타뎀 0
        if self.monster.isBoss is True and self.player.isEquip_HU is True and self.bossFirstAttack is True:
            self.bossFirstAttack = False # 첫타 끝~
        else:
            # 플레이어는 스파이크트랩에도 죽을 수 있어서 플레이어 내에서 처리하는 게 나은듯
            # 어차피 맞아죽는건 player 안에서 구현해뒀음 근데 리인카 터지는 여부 봐야해서 if로 묶음
            if self.player.hitDamage(max(1, self.monster.weapon - self.player.armor - self.player.equip_armor)):
                # 리인카 터지면 몬스터도 회복함
                self.monster.heal()
                return # 리인카 터지면 싸움 끝
        
        

        # 플레이어 차례로 넘김
        self.playerAttack()

def gameProcess():
    global Turn
    global player
    K = 0
    L = 0
    for row in range(1, N + 1):
        temp = ' ' + input()
        for col in range(1, M + 1):
            if temp[col] == '.' or temp[col] == '^':
                Map[row][col] = temp[col]
            elif temp[col] == '&':
                K += 1
            elif temp[col] == 'B':
                L += 1
            elif temp[col] == '@':
                Map[row][col] = '.'
                player = Player(row, col)
            elif temp[col] == 'M':
                Map[row][col] = 'M'
                K += 1
    
    Order = deque(list(input()))

    # 몬스터 위치정보 등록
    for _ in range(K):
        mInfo = input().split()
        Map[int(mInfo[0])][int(mInfo[1])] = Monster(int(mInfo[0]), int(mInfo[1]), mInfo[2], int(mInfo[3]), int(mInfo[4]), int(mInfo[5]), int(mInfo[6]))

    # 상자 위치정보 등록
    for _ in range(L):
        bInfo = input().split()
        try:
            Map[int(bInfo[0])][int(bInfo[1])] = ItemBox(bInfo[2], int(bInfo[3]))
        except:
            Map[int(bInfo[0])][int(bInfo[1])] = ItemBox(bInfo[2], bInfo[3])
    
    # 움직일 게 남아있으면 움직임
    while len(Order) > 0:
        Turn += 1
        player.Move(Order.popleft())
    printMap()
    player.printInfo()
    print('Press any key to continue.')



def printMap():
    for row in range(1, N + 1):
        for col in range(1, M + 1):
            if row == player.nowLocation[0] and col == player.nowLocation[1]:
                if player.hp > 0:
                    print('@', end='')
                    continue
            if Map[row][col] == '.':
                print('.', end='')
            elif Map[row][col] == '^':
                print('^', end='')
            elif Map[row][col] == '#':
                print('#', end='')
            elif type(Map[row][col]) is Monster and Map[row][col].isBoss is True:
                print('M', end='')
            elif type(Map[row][col]) is Monster and Map[row][col].isBoss is False:
                print('&', end='')
            elif type(Map[row][col]) is ItemBox:
                print('B', end='')
            else:
                Map[10000] = 100 # 고의로 인덱스 에러내기
                print('내가놓친거 뭐 있나 봐봐')
        print()


gameProcess()