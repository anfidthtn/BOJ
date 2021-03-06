# [Platinum I] 이진 행렬 - 2324 

[문제 링크](https://www.acmicpc.net/problem/2324) 

### 성능 요약

메모리: 131888 KB, 시간: 232 ms

### 분류

너비 우선 탐색(bfs), 그래프 이론(graphs), 그래프 탐색(graph_traversal), 휴리스틱(heuristics)

### 문제 설명

<p>어떤 n×m(1 ≤ n, m ≤ 100)의 이진 행렬이 있다. 이진 행렬이라는 것은 0과 1로만 된 행렬이라는 뜻이다.</p>

<p>이 행렬에서 Reverse(x, y)라는 연산이 정의되어 있다. 행렬의 (x, y)에 원래 저장되어 있던 값을 k라고 하자. 이 함수를 수행하면 (x, y)와 인접(상하좌우)해 있는 칸들 중에서 그 행렬 값이 k인 위치로 퍼져 나가면서, 행렬 값을 1-k(즉, 0을 1로, 1을 0으로)로 바꾼다. x는 행 번호, y는 열 번호이다. 행렬의 행 번호와 열 번호는 1부터 시작한다.</p>

<p>예를 들어 11000111과 같은 1×8의 행렬이 있다고 해 보자. Reverse(1,1)을 수행하면 00000111이 되고, Reverse(1,4)를 수행하면 11111111이 된다.</p>

<p>이와 같은 Reverse 함수를 최소로 이용하여, 전체 행렬에 저장되어 있는 값을 같은 값(0 또는 1)로 바꾸는 것이 목적이다.</p>

### 입력 

 <p>첫째 줄에 n, m이 주어진다. 다음 n개의 줄에는 m개의 수로 행렬이 주어진다.</p>

### 출력 

 <p>첫째 줄에 함수의 호출 회수 K를 출력한다. 다음 K개의 줄에는 x, y를 출력한다. 이는 차례로 Reverse(x, y)의 함수를 호출하였다는 의미이다.</p>

