N, M = map(int, input().split())

class Node:
    def __init__(self):
        self.isArrested = False
        self.isRoot = True
        self.children = []
        self.parentCount = 0

    def linkChild(self, char):
        self.children.append(char)
        drug[char].parentCount += 1
        drug[char].isRoot = False
    
    def arrested(self):
        for child in self.children:
            child = drug[child]
            if child.isArrested is False:
                child.parentCount -= 1
                if child.parentCount <= 0:
                    child.arrested()
        self.isArrested = True

drug = {}
for n in range(N):
    drug[chr(n + ord('A'))] = Node()

for _ in range(M):
    st, de = input().split()
    drug[st].linkChild(de)

arrest = input().split()[1:]
for char in arrest:
    if drug[char].isArrested is False:
        drug[char].arrested()

count = 0
for n in range(N):
    if drug[chr(n + ord('A'))].isArrested is False and drug[chr(n + ord('A'))].isRoot is False:
        count += 1
print(count)