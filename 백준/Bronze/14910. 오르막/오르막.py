nums = list(map(int, input().split()))
a = sorted(nums)
def solve():
    for i in range(len(nums)):
        if a[i] != nums[i]:
            print("Bad")
            return
    print("Good")

solve()