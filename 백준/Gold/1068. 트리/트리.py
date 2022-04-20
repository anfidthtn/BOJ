class Node:
    def __init__(self):
        self.state = True
        self.child = []


N = int(input())
PList = list(map(int, input().split()))
NList = []

for _ in range(N):
    NList.append(Node())

roots = []

for idx, p in enumerate(PList):
    if p == -1:
        roots.append(NList[idx])
    else:
        NList[p].child.append(NList[idx])

delNode = int(input())

NList[delNode].state = False

count = 0

while len(roots) > 0:
    node = roots.pop()
    if node.state is True:
        if len(node.child) == 0:
            count += 1
        elif len(node.child) == 1 and node.child[0].state is False:
            count += 1
        else:
            for childNode in node.child:
                roots.append(childNode)

print(count)