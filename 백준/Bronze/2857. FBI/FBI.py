count = 0
for i in range(1, 6):
  if input().count("FBI") > 0:
    count += 1
    print(i, end=" ")
if count == 0:
  print("HE GOT AWAY!")