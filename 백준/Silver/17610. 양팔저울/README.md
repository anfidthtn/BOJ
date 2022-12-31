# [Silver I] 양팔저울 - 17610 

[문제 링크](https://www.acmicpc.net/problem/17610) 

### 성능 요약

메모리: 11272 KB, 시간: 28 ms

### 분류

브루트포스 알고리즘(bruteforcing)

### 문제 설명

<p>무게가 서로 다른 k개의 추와 빈 그릇이 있다. 모든 추의 무게는 정수이고, 그릇의 무게는 0으로 간주한다. 양팔저울을 한 번만 이용하여 원하는 무게의 물을 그릇에 담고자 한다. 주어진 모든 추 무게의 합을 S라 하자. 예를 들어, 추가 3개이고 그 무게가 각각 {1, 2, 6}이면, S = 9이고, 양팔 저울을 한번만 이용하여 1부터 S사이 모든 정수에 대응하는 물을 다음과 같이 그릇에 담을 수 있다. 여기서, X는 그릇에 담는 물의 무게를 나타내고, □는 그릇을 나타낸다.</p>

<table class="table table-bordered sn-table" style="width: 100%;">
	<thead>
		<tr>
			<th style="width: 10%; text-align: center;">X</th>
			<th style="width: 10%; text-align: center;">1</th>
			<th style="width: 10%; text-align: center;">2</th>
			<th style="width: 10%; text-align: center;">3</th>
			<th style="width: 10%; text-align: center;">4</th>
			<th style="width: 10%; text-align: center;">5</th>
			<th style="width: 10%; text-align: center;">6</th>
			<th style="width: 10%; text-align: center;">7</th>
			<th style="width: 10%; text-align: center;">8</th>
			<th style="width: 10%; text-align: center;">9</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="text-align: center;"> </td>
			<td style="text-align: center;">□:1</td>
			<td style="text-align: center;">□:2</td>
			<td style="text-align: center;">□:(1+2)</td>
			<td style="text-align: center;">(□+2):6</td>
			<td style="text-align: center;">(□+1):6</td>
			<td style="text-align: center;">□:6</td>
			<td style="text-align: center;">□:(1+6)</td>
			<td style="text-align: center;">□:(2+6)</td>
			<td style="text-align: center;">□:(1+2+6)</td>
		</tr>
	</tbody>
</table>

<p>만약 추의 무게가 {1, 5, 7}이면 S = 13이 되고, 양팔저울을 한 번만 사용하여 그릇에 담을 수 있는 무게는 {1, 2, 3, 4, 5, 6, 7, 8, 11, 12, 13}이다. 즉, 1부터 S사이 수 가운데 9와 10에 대응하는 무게의 물을 그릇에 담는 것은 불가능하다.</p>

<p>k(3 ≤ k ≤ 13)개 추 무게 g<sub>1</sub>, g<sub>2</sub>, ..., g<sub>k</sub>가 주어질 때, 1부터 S사이에 있는 정수 중, 양팔 저울을 한번만 이용하여서는 측정이 불가능한 경우의 수를 찾는 프로그램을 작성하고자 한다.</p>

### 입력 

 <p>입력의 첫 줄에는 추의 개수를 나타내는 정수 k(3 ≤ k ≤ 13)가 주어진다. 다음 줄에는 k개의 정수 g<sub>i</sub>(1 ≤ g<sub>i</sub> ≤ 200,000)가 공백으로 구분되어 주어지는데 이는 각 추의 무게를 나타낸다.</p>

### 출력 

 <p>1부터 S(추 무게의 합) 사이에 있는 정수 중, 양팔 저울을 한번만 이용해서는 측정이 불가능한 경우의 수를 출력한다.</p>

