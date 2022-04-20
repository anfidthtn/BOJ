n = int(input())
nums = list(map(int, input().split()))
expCount = list(map(int, input().split()))
expression = []

for i in range(n):
    expression.append(nums[i])
    expression.append(None)
expression.pop()

minValue = 10 ** 10
maxValue = -10 ** 10

def calcExp():
    global minValue
    global maxValue
    exp = ""
    for item in expression:
        exp += str(item)
    try:
        result = eval(exp)
        minValue = min(minValue, result)
        maxValue = max(maxValue, result)
    except:
        pass

def recu(insertIdx):
    if insertIdx == 2 * n - 1:
        calcExp()
        return
    for idx, symbol in enumerate(['+', '-', '*', '//']):
        if expCount[idx] > 0:
            expCount[idx] -= 1
            expression[insertIdx] = symbol
            recu(insertIdx + 2)
            expCount[idx] += 1

recu(1)
print(maxValue)
print(minValue)