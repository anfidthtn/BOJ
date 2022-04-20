import sys
from collections import deque

N = int(sys.stdin.readline())

name_list = {}
'''
name to idx, idx to name 둘 다 원활하게 하기 위해 dictionary에 'idx:name', 'name:idx' 동시저장
혹시나 웹사이트 이름이 1, 2와같은 숫자일 순 있지만 입력 받을 때
String 형태로 입력받아서 type이 달라 중복우려가 없다.
'''
'''
link_list => 0->1 이면 [[],[0]]
graph => 0->1 이면 [[1],[]]
'''
link_list = [] # linked list 아님! 그냥 링크의 리스트임!
graph = []
score_list = []

name_list_idx = 0


# 중복되지 않은 웹 사이트가 나왔을 때, 인덱싱을 하고 정점 추가를 시키는 함수
def append_name_list(name):
    global name_list_idx
    name_list[name] = name_list_idx
    name_list[name_list_idx] = name
    name_list_idx += 1
    link_list.append([])
    graph.append([])
    score_list.append(1)


# 사실 안해도 되는데 더 가시적으로 보기 위해 함수로 제작
def name2idx(name):
    return name_list[name]


def idx2name(idx):
    return name_list[idx]


'''
N개의 규칙만큼 반복하며 그래프 그림
'''
for _ in range(N):
    temp = list(map(str, sys.stdin.readline().split()))
    for idx, i in enumerate(temp):
        # 링크 개수 패스
        if idx == 1:
            continue
        # 새로운 문자열이 나오면 해당 문자열의 인덱스 등록과 함께 그래프의 정점을 추가함
        if i not in name_list:
            append_name_list(i)
    # 링크 웹사이트를 등록
    for i in temp[2:]:
        link_list[name2idx(temp[0])].append(i)
        graph[name2idx(i)].append(temp[0])

node_length = len(link_list)

Target = str(sys.stdin.readline().split()[0])

'''
사이클 파악, 사이클 제거작업
'''

'''
# 서로 순환하는 녀석들끼리 대표번호로 묶음
ex) [0, 0, 0, 2, 2, 5, 5, 5]로 나온다면
0~2에서 순환, 2~3에서 순환, 5~7에서 순환이 있어서 해당 순환노드에서 상호간의 간선을 제거한다.
'''
set_num_list = [i for i in range(node_length)]

# 이미 집합으로 묶인 녀석들 사이에 재귀가 돌아가지 않게 방지하는 리스트
non_explored_node = [i for i in range(node_length)]


def make_set(visited, now_idx):
    # 이미 집합관계가 파악된 녀석은 돌지 않는다.
    if now_idx not in non_explored_node:
        return
    # copy로 참조복사를 막는다.
    now_visited = visited.copy()
    # 자신이 방문했음을 기록한다.
    now_visited += [now_idx]

    next_length = len(graph[now_idx])
    if next_length == 0:
        if now_idx in non_explored_node:
            non_explored_node.remove(now_idx)

    # 자신 다음 위치(자신이 링크하고 있는 사이트)에 대해 for문을 돈다.
    for edge in graph[now_idx]:
        # edge 는 str형 name 이므로 int형의 index로 바꾼다.
        next_idx = name2idx(edge)
        # print(now_idx, next_idx, '\n', now_visited, '\n', non_explored_node, '\n', set_num_list)
        # search중인 depth에서 다음 갈 링크 사이트가 이미 방문한 곳이라면 들어가서 사이클로 처리한다.
        if set_num_list[next_idx] in now_visited:
            # print(set_num_list, now_visited, now_idx, next_idx)
            # 사이클의 시작점을 잡고 시작점부터 처리.
            cycle_start_idx = now_visited.index(set_num_list[next_idx])
            for cycle_idx in now_visited[cycle_start_idx:]:
                '''
                사이클 처리는 사이클 시작점의 집합의 번호 등록
                (이미 next_idx가 다른 집합에 속해있을 수 있어서 next_idx가 아닌 set_num_list[next_idx]를 등록)
                '''
                set_num_list[cycle_idx] = set_num_list[next_idx]
                # 집합에 넣은 지점을 미탐색 노드에서 뺀다.
                if cycle_idx in non_explored_node:
                    non_explored_node.remove(cycle_idx)
        else:
            # print(set_num_list, now_visited, now_idx, next_idx)
            # 집합관계가 파악된 녀석은 돌지 않는다.
            if next_idx not in non_explored_node:
                next_length -= 1
                if next_length == 0:
                    if now_idx in non_explored_node:
                        non_explored_node.remove(now_idx)
                    break
                else:
                    continue
            # 사이클이 없다면 다음 지점으로 더 깊은 depth를 탐색한다.
            make_set(now_visited, next_idx)


for idx in range(node_length):
    # 이미 집합화 한 놈은 돌지 않는다.
    if non_explored_node.count(idx) > 0:
        visited = []
        make_set(visited, idx)

'''
[0, 0, 1, 1, 1, 1] 이면
[[0 ~ 1], [], [2 ~ 5], [], [], []] 로 만들어서
0, 1에서 0, 1로
2 ~ 5에서 2 ~ 5로 향하는 노드들(사이클과 관련된 모든 노드들)을 제거할 준비를 하는 리스트다.
'''
remove_list = [[] for _ in range(len(set_num_list))]
for idx, i in enumerate(set_num_list):
    remove_list[i].append(idx2name(idx))

'''

'''
for idx, i in enumerate(set_num_list):
    link_list[idx] = list(set(link_list[idx]) - set(remove_list[i]))
    graph[idx] = list(set(graph[idx]) - set(remove_list[i]))

dq = deque()

indegree = []

for idx, i in enumerate(link_list):
    indegree.append(len(i))
    if len(i) == 0:
        dq.append(idx)

while len(dq) > 0:
    now_idx = dq.pop()
    for link_name in graph[now_idx]:
        link_idx = name2idx(link_name)
        score_list[link_idx] += score_list[now_idx]
        indegree[link_idx] -= 1
        if indegree[link_idx] == 0:
            dq.append(link_idx)

# print(score_list)
# print(set_num_list)
print(score_list[name2idx(Target)])
