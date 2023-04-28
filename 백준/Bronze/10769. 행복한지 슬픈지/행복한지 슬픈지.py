i = input()
h = i.count(":-)")
s = i.count(":-(")
if h + s == 0:
    print("none")
elif h == s:
    print("unsure")
elif h < s:
    print("sad")
else:
    print("happy")