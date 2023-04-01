# [Gold II] 이상한 토너먼트 - 15321 

[문제 링크](https://www.acmicpc.net/problem/15321) 

### 성능 요약

메모리: 15924 KB, 시간: 304 ms

### 분류

다이나믹 프로그래밍, 그리디 알고리즘

### 문제 설명

<p>천하제일코딩대회가 열리게 되었다. 천하제일코딩대회는 1대1 대결 토너먼트 방식으로 진행이 된다. 대회 운영을 맡은 전설의 코더 천민호는 개인의 코딩실력을 정확히 측정하는 스카우터를 만들어 각 참가자의 코딩력(능력치)을 알고 있다. 코딩력이 다른 두 사람이 대결을 하게되면 무조건 코딩력이 높은 사람이 승리하게 된다. (사실상 우승자는 정해져있는 것이나 다름없다.)</p>

<p>토너먼트 대진표를 작성하기 귀찮았던 민호는 우선 참가신청한 사람들의 순서대로 일렬로 나열한 후, 선을 그어 대진표를 완성하려 한다. 민호는 대회의 재미를 위해서 관중들이 지루하지 않도록 대진표를 완성하고 싶다. 일반적으로 관중들의 지루함 정도는 두 대결자의 코딩력의 차에 비례한다. </p>

<p style="text-align:center">관중들의 지루함 = 두 대결자의 코딩력 차</p>

<p>라고 하였을 때, 모든 토너먼트가 끝난 후 지루함의 합이 최소가 되도록 대진표를 작성하려 한다. 이때, 지루함의 합을 구하여라.</p>

<ul>
	<li>대진표의 선을 어떻게 작성하냐에 따라 각 참가자의 경기수는 다를 수 있다.</li>
	<li>대진표의 선이 교차되게 작성하면 안 된다. (이웃한 두 그룹 간 대결할 수 있다.)</li>
</ul>

<table class="table table-bordered" style="width:100%">
	<tbody>
		<tr>
			<td style="text-align:center; width:50%"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15321/1.png" style="height:143px; width:208px"></td>
			<td style="text-align:center; width:50%"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15321/2.png" style="height:148px; width:214px"></td>
		</tr>
		<tr>
			<td style="text-align:center; width:50%">그림1  최적의 경우 (총 지루함:1997)</td>
			<td style="text-align:center; width:50%">그림2 최악의 경우 (총 지루함:7848)</td>
		</tr>
	</tbody>
</table>

<p>위 그림과 같은 경우 그림 1의 방식으로 대진표를 작성하였을 때, 총 지루함은 1997이지만 그림 2의 방식으로 대진표를 작성하였을 때, 총 지루함은 7848이 된다.</p>

### 입력 

 <p>첫째 줄에 총 참가자의 수 N(2 ≤ N ≤ 500)이 주어진다. 그 후 N줄에 걸쳐 참가신청한 순서에 맞추어 코딩력 X<sub>i</sub> (1 ≤ X<sub>i</sub> ≤ 100,000)이 주어진다. 코딩력이 같은 참가자는 없다.</p>

### 출력 

 <p>대진표를 최적의 경우로 작성하였을 때, 총 지루함의 값을 출력한다.</p>

