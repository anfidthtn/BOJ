# [Gold V] Drop 7 - 25331 

[문제 링크](https://www.acmicpc.net/problem/25331) 

### 성능 요약

메모리: 11680 KB, 시간: 76 ms

### 분류

구현(implementation), 시뮬레이션(simulation)

### 문제 설명

<p>Drop7은 7×7 크기의 격자에서 진행하는 싱글 플레이어 게임이다. 처음에는 격자가 비어있고, 플레이어는 매 턴마다 1 이상 7 이하의 정수 하나가 적힌 공을 받아 7개의 열 중 한 곳에 떨어뜨려야 한다. 떨어뜨린 공은 이미 배치되어 있는 공 바로 위에 도달하거나 바닥 바로 위에 도달할 때까지 아래로 이동한다.</p>

<p>가로 방향으로 연속해서 놓여있는 공들을 "가로 방향 그룹", 세로 방향으로 연속해서 놓여있는 공들을 "세로 방향 그룹"이라고 하자. 공을 떨어뜨릴 때마다 공들의 그룹이 바뀔 수 있는데, 만약 크기가 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D465 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>x</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$x$</span></mjx-container>인 그룹에 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D465 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>x</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$x$</span></mjx-container>가 적힌 공이 있다면 그 공은 없어진다. 조건을 만족하는 공은 모두 동시에 없어진다. 공이 없어지면 위에 있던 공들이 아래로 내려가며, 공이 없어지는 이벤트는 연쇄적으로 발생한다. 단, 크기가 n인 그룹은 크기가 n-1인 그룹을 포함하지 않는다. 즉 그룹의 가장 끝에 위치한 공은 격자의 테두리 또는 빈 공간과 인접한다.</p>

<p>현재 격자의 상태와 이번에 떨어뜨려야 하는 공의 번호가 주어졌을 때, 공을 한 번 떨어뜨려서 최대한 많이 공을 없애보자.</p>

### 입력 

 <p>첫째 줄부터 7개의 줄에 현재 격자의 상태가 주어진다. 공이 있는 칸은 공의 번호가 1부터 7까지의 숫자로 주어지며, 공이 없는 칸은 0으로 주어진다.</p>

<p>다음 줄에 떨어뜨릴 공의 번호가 주어진다.</p>

<p>게임 도중에 나타날 수 있는 상태만 입력으로 주어진다. 즉 격자의 첫 번째 줄은 모두 0이고, 공을 떨어뜨리기 전에 공이 없어지는 이벤트가 발생하지 않는 입력만 주어진다.</p>

### 출력 

 <p>공을 떨어뜨렸을 때 격자에 남아있을 수 있는 공의 개수의 최솟값을 출력한다.</p>

