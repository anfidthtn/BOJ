# [Platinum IV] 2,3 거듭제곱의 합 - 2727 

[문제 링크](https://www.acmicpc.net/problem/2727) 

### 성능 요약

메모리: 29452 KB, 시간: 76 ms

### 분류

애드 혹(ad_hoc), 구성적(constructive), 수학(math), 재귀(recursion)

### 문제 설명

<p>모든 자연수 N은 (2<sup>a</sup>)(3<sup>b</sup>)의 합으로 나타낼 수 있다. 이때, 서로 약수/배수 관계인 두 항이 있어서는 안 된다.</p>

<ul>
	<li>1 = (2<sup>0</sup>)(3<sup>0</sup>)</li>
	<li>7 = (2<sup>2</sup>)(3<sup>0</sup>) + (2<sup>0</sup>)(3<sup>1</sup>)</li>
	<li>31 = (2<sup>4</sup>)(3<sup>0</sup>) + (2<sup>0</sup>)(3<sup>2</sup>) + (2<sup>1</sup>)(3<sup>1</sup>) = (2<sup>2</sup>)(3<sup>0</sup>) + (2<sup>0</sup>)(3<sup>3</sup>)</li>
</ul>

<p>N이 주어졌을 때, 이를 (2<sup>a</sup>)(3<sup>b</sup>)의 합으로 나타내는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 테스트 케이스의 개수 T(1 ≤ T ≤ 1,000)가 주어진다. 각 테스트 케이스는 한줄로 이루어져 있고, 정수 N이 주어진다. (1 ≤ N < 2<sup>31</sup>)</p>

### 출력 

 <p>각 테스트 케이스에 대해 첫째 줄에는 항의 개수 M을 출력한다. 그다음 M개의 줄에 걸쳐 합을 구성하는 항을 출력한다. 어떤 항이 (2<sup>a</sup>)(3<sup>b</sup>)일때, a b를 공백으로 구분하여 출력하면 된다.</p>

