import sys
sys.setrecursionlimit(1<<20)
input = sys.stdin.readline

# 대충 양방향 그래프 만든다.
N = int(input())
graph = [set() for _ in range(N + 1)]
for _ in range(N - 1):
    a, b = map(int, input().split())
    graph[a].add(b)
    graph[b].add(a)


nums = list(map(int,input().split()))
# 스택 만든다 (0 -> 트리루트 -> ... -> 현재 방문하는 노드까지 길이 생기도록 하는 것)
st = [0]
# 안 쓰는 숫자 0번 -> 첫 항(트리 루트) 연결한다.
graph[0].add(1)

def solve():
    global st

    for num in nums:
        # 해당 번호를 방문하려 할 때 현재 방문한 노드의 자식목록에 해당 번호가 없으면 pop으로 방문 노드를 거슬러간다.
        while len(st) > 0 and num not in graph[st[-1]]:
            st.pop()
        # 더 거슬러 올라갈 방문 노드가 없다면 불가능이다.
        if len(st) == 0:
            return 0
        # 자식 목록에 해당 번호가 있는 경우까지 거슬러 왔으면 해당 번호를 방문한다.
        st.append(num)
    # N개를 모두 방문했다면 가능이다.
    return 1

print(solve())