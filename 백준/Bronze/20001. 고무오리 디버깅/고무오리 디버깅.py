input()
st = []
while True:
    s = input()
    if s == "고무오리 디버깅 끝":
        break
    elif s == "문제":
        st.append(1)
    elif not st:
        st.append(1)
        st.append(1)
    else:
        st.pop()

if not st:
    print("고무오리야 사랑해")
else:
    print("힝구")