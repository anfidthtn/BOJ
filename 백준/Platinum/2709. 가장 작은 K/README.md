# [Platinum III] 가장 작은 K - 2709 

[문제 링크](https://www.acmicpc.net/problem/2709) 

### 성능 요약

메모리: 30840 KB, 시간: 72 ms

### 분류

임의 정밀도 / 큰 수 연산(arbitrary_precision), 분할 정복을 이용한 거듭제곱(exponentiation_by_squaring), 수학(math), 정수론(number_theory)

### 문제 설명

<p>R이 주어졌을 때, 마지막 R자리가 1과 2로만 이루어진 가장 작은 2<sup>k</sup>를 구하는 프로그램을 작성하시오. </p>

<p>예를 들어, 2<sup>9</sup> = 512이고, 2<sup>89</sup> = 618970019642690137449562112 이다. 2<sup>9</sup>는 마지막 2자리가 1과 2로 이루어져 있고, 2<sup>89</sup>는 마지막 4자리가 1과 2로만 이루어져 있다.</p>

<p>R이 6일때까지 답을 구해보면 다음과 같다.</p>

<table class="table table-bordered" style="width:30%;">
	<thead>
		<tr>
			<th style="width:10%;">R</th>
			<th style="width:10%;">가장 작은 k</th>
			<th style="width:10%;">2<sup>k</sup></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>1</td>
			<td>1</td>
			<td>2</td>
		</tr>
		<tr>
			<td>2</td>
			<td>9</td>
			<td>512</td>
		</tr>
		<tr>
			<td>3</td>
			<td>89</td>
			<td>...112</td>
		</tr>
		<tr>
			<td>4</td>
			<td>89</td>
			<td>...2112</td>
		</tr>
		<tr>
			<td>5</td>
			<td>589</td>
			<td>...22112</td>
		</tr>
		<tr>
			<td>6</td>
			<td>3089</td>
			<td>...122112</td>
		</tr>
	</tbody>
</table>

### 입력 

 <p>첫째 줄에 테스트 케이스의 개수 T(1 ≤ T ≤ 50)가 주어진다. 각 테스트 케이스는 정수 1개로 이루어져 있고, 이 수는 R(1 ≤ R ≤ 20)이다.</p>

### 출력 

 <p>각 테스트 케이스에 대해 한 줄에 하나씩 마지막 R자리가 1과 2로만 이루어진 가장 2<sup>k</sup>의 k를 출력한다.</p>

