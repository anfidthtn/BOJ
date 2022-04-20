N = int(input())
Nums = list(map(int, input().split()))


def quick_sorted(arr):
    if len(arr) > 1:
        pivot = arr[len(arr)-1]
        left, mid, right = [], [], []
        for i in range(len(arr)-1):
            if arr[i] < pivot:
                left.append(arr[i])
            elif arr[i] > pivot:
                right.append(arr[i])
            else:
                mid.append(arr[i])
        mid.append(pivot)
        return quick_sorted(left) + mid + quick_sorted(right)
    else:
        return arr


Nums = quick_sorted(Nums)
Nums.reverse()


def printnum():
    print(Nums.pop(), end=' ')


while len(Nums) > 0:
    if len(Nums) == 1:
        printnum()
    elif len(Nums) >= 2:
        if Nums[-1] == Nums[0] and Nums.count(Nums[-1]) == len(Nums):
            printnum()
        elif Nums[-1] + 1 == Nums[0] and len(Nums) == Nums.count(Nums[-1]) + Nums.count(Nums[0]):
            while len(Nums) > 0:
                print(Nums.pop(0), end=' ')
        elif Nums[-1] + 1 == Nums[-2]:
            i = 2
            while Nums[-i] == Nums[-i - 1]:
                i += 1
            Nums[-2], Nums[-i - 1] = Nums[-i - 1], Nums[-2]
            printnum()
        else:
            printnum()