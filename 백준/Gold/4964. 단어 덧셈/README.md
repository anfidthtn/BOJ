# [Gold V] 단어 덧셈 - 4964 

[문제 링크](https://www.acmicpc.net/problem/4964) 

### 성능 요약

메모리: 12284 KB, 시간: 7264 ms

### 분류

백트래킹(backtracking), 브루트포스 알고리즘(bruteforcing), 수학(math), 문자열(string)

### 문제 설명

<p>단어 덧셈이란 905 + 125 = 1030과 같은 덧셈의 각 숫자를 알파벳으로 바꾼 것이다.</p>

<p>9를 A로, 0을 C로, 5를 M으로, 1을 I로, 2를 B로, 0을 C로, 3을 P로 바꾸면 다음과 같이 된다.</p>

<p>ACM + IBM = ICPC</p>

<p>이렇게 905 + 125 = 1030의 경우에는 바꾸는 방법이 총 4가지가 있다.</p>

<table class="table table-bordered" style="width:28%">
	<thead>
		<tr>
			<th style="width:10%">방법</th>
			<th style="width:3%">A</th>
			<th style="width:3%">B</th>
			<th style="width:3%">C</th>
			<th style="width:3%">I</th>
			<th style="width:3%">M</th>
			<th style="width:3%">P</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>방법 1</td>
			<td>9</td>
			<td>2</td>
			<td>0</td>
			<td>1</td>
			<td>5</td>
			<td>3</td>
		</tr>
		<tr>
			<td>방법 2</td>
			<td>9</td>
			<td>3</td>
			<td>0</td>
			<td>1</td>
			<td>5</td>
			<td>4</td>
		</tr>
		<tr>
			<td>방법 2</td>
			<td>9</td>
			<td>6</td>
			<td>0</td>
			<td>1</td>
			<td>5</td>
			<td>7</td>
		</tr>
		<tr>
			<td>방법 2</td>
			<td>9</td>
			<td>7</td>
			<td>0</td>
			<td>1</td>
			<td>5</td>
			<td>8</td>
		</tr>
	</tbody>
</table>

<p> </p>

<p>단어 덧셈이 주어졌을 때, 이 단어 덧셈을 푸는 방법의 수를 구하는 프로그램을 작성하시오. 단, 다음 조건을 만족해야 한다.</p>

<p>1. 덧셈의 각 항은 숫자 '0'-'9'로 이루어져 있다. 모든 숫자는 알파벳 'A'-Z'로 바뀌어져 있다.</p>

<p>2. 한 알파벳은 하나의 숫자만을 나타낼 수 있다. 또, 어떤 숫자에 해당하는 알파벳은 반드시 하나 이어야 한다.</p>

<p>3. 0을 제외한 숫자는 0으로 시작할 수 없다. 즉, 00이나 0123과 같은 경우는 허용하지 않는다.</p>

<p> </p>

### 입력 

 <p>입력은 여러 개의 테스트 케이스로 이루어져 있다. 테스트 케이스의 마지막 줄에는 0이 주어진다.</p>

<p>각 테스트 케이스는 첫째 줄에 단어의 개수 N이 주어지고, 그 다음 줄에는 단어 N개가 주어진다. 단어는 알파벳 'A'-'Z'로만 이루어져 있다.</p>

<p>각 단어는 다음과 같은 방정식을 나타낸다.</p>

<p>단어<sub>1</sub>+단어<sub>2</sub>+...+단어<sub>N-1</sub> = 단어<sub>N</sub></p>

<p>N은 2보다 크고, 13보다 작다. 단어의 길이는 0보다 크고, 9보다 작다. 단어에 등장하는 서로 다른 알파벳의 개수는 0보다 크고, 11보다 작다.</p>

### 출력 

 <p>각 테스트 케이스에 대해서, 단어 덧셈을 풀 수 있는 방법의 수를 출력한다.</p>

