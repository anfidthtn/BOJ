# [Gold III] 사기 주사위 - 26603 

[문제 링크](https://www.acmicpc.net/problem/26603) 

### 성능 요약

메모리: 15020 KB, 시간: 136 ms

### 분류

브루트포스 알고리즘(bruteforcing)

### 문제 설명

<p>기성이와 민우는 가끔 주사위를 굴려 나오는 숫자에 따라 벌칙을 받는 게임을 한다. 어느 날, 민우의 주사위로 게임을 할 때마다 이상할 정도로 민우에게 유리한 숫자가 나오는 것이 수상했던 기성이는 혹시 민우의 주사위가 숫자를 조작할 수 있는 사기 주사위가 아닌지 의심하기 시작했다.</p>

<p>민우의 주사위는 정팔면체 주사위로 1부터 8까지의 숫자가 각 면에 적혀 있고, 주사위를 굴린 후 위에서 보면 다음과 같이 정육각형 모양으로 4개의 면이 보인다. </p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/68659646-db0b-4f7a-a789-5df1491f214c/-/preview/" style="width: 100px; height: 112px;"></p>

<p>기성이는 매번 주사위를 굴릴 때마다 보이는 4개의 숫자를 기억하고 있다가 어떤 정팔면체 주사위도 이러한 조합을 만족할 수 없다는 것을 증명해 민우의 주사위가 사기 주사위라는 것을 밝히려고 한다. 기성이가 기억하는 숫자의 조합이 주어질 때, 민우의 주사위가 사기 주사위인지 판별하는 프로그램을 작성하시오.</p>

### 입력 

 <p>주사위를 굴린 횟수 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>이 첫 줄에 주어진다. <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mo class="mjx-n"><mjx-c class="mjx-c28"></mjx-c></mjx-mo><mjx-mn class="mjx-n"><mjx-c class="mjx-c31"></mjx-c></mjx-mn><mjx-mo class="mjx-n" space="4"><mjx-c class="mjx-c2264"></mjx-c></mjx-mo><mjx-mi class="mjx-i" space="4"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi><mjx-mo class="mjx-n" space="4"><mjx-c class="mjx-c2264"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="4"><mjx-c class="mjx-c31"></mjx-c></mjx-mn><mjx-mstyle><mjx-mspace style="width: 0.167em;"></mjx-mspace></mjx-mstyle><mjx-mn class="mjx-n"><mjx-c class="mjx-c30"></mjx-c><mjx-c class="mjx-c30"></mjx-c><mjx-c class="mjx-c30"></mjx-c></mjx-mn><mjx-mo class="mjx-n"><mjx-c class="mjx-c29"></mjx-c></mjx-mo></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mo stretchy="false">(</mo><mn>1</mn><mo>≤</mo><mi>N</mi><mo>≤</mo><mn>1</mn><mstyle scriptlevel="0"><mspace width="0.167em"></mspace></mstyle><mn>000</mn><mo stretchy="false">)</mo></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$(1 \leq N \leq 1\,000)$</span> </mjx-container></p>

<p>두 번째 줄부터 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container>개의 줄에 걸쳐 주사위를 위에서 봤을 때 보이는 4개의 서로 다른 숫자가 공백으로 구분되어 주어진다.</p>

### 출력 

 <p>사기 주사위가 맞다면 <code>You're gonna die!</code>를 출력하고 증거를 찾지 못했다면 <code>Hmm...</code>을 출력한다.</p>

