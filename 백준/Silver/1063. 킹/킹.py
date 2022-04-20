direction = {'R' : (0, 1), 'L' : (0, -1), 'B' : (-1, 0), 'T' : (1, 0), 'RT' : (1, 1), 'LT' : (1, -1), 'RB' : (-1, 1), 'LB' : (-1, -1)}

def pos2RowCol(pos): # pos : A1, F3, ... => cord : (1, 1), (3, 6), ...
    col = ord(pos[0]) - ord('A') + 1
    row = int(pos[1])
    return [row, col]

def rowcol2pos(cord): # cord : (row, col)
    return chr(cord[1] - 1 + ord('A')) + str(cord[0])

kingPos, stonePos, n = input().split()

class statue:
    def __init__(self, pos):
        self.row, self.col = pos2RowCol(pos)
    
    def move(self, next):
        self.row += direction[next][0]
        self.col += direction[next][1]
    
    def getPos(self):
        return rowcol2pos((self.row, self.col))


class Stone(statue):
    def isMovable(self, next):
        nextRow = self.row + direction[next][0]
        nextCol = self.col + direction[next][1]
        if nextRow < 1 or nextRow > 8:
            return False
        if nextCol < 1 or nextCol > 8:
            return False
        return True

stone = Stone(stonePos)

class King(statue):
    def tryMove(self, next, stone):
        nextRow = self.row + direction[next][0]
        nextCol = self.col + direction[next][1]
        if nextRow < 1 or nextRow > 8:
            return
        if nextCol < 1 or nextCol > 8:
            return
        if stone.row == nextRow and stone.col == nextCol:
            if stone.isMovable(next) is False:
                return
            else:
                stone.move(next)
        self.move(next)

king = King(kingPos)

for _ in range(int(n)):
    next = input()
    king.tryMove(next, stone)

print(king.getPos())
print(stone.getPos())