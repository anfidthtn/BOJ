# [Gold V] Heavy Cargo - 6589 

[문제 링크](https://www.acmicpc.net/problem/6589) 

### 성능 요약

메모리: 18572 KB, 시간: 204 ms

### 분류

자료 구조(data_structures), 다익스트라(dijkstra), 그래프 이론(graphs), 해시를 사용한 집합과 맵(hash_set), 문자열(string)

### 문제 설명

<p><i>Big Johnsson Trucks Inc.</i> is a company specialized in manufacturing big trucks. Their latest model, the <i>Godzilla V12</i>, is so big that the amount of cargo you can transport with it is never limited by the truck itself. It is only limited by the weight restrictions that apply for the roads along the path you want to drive.</p>

<p>Given start and destination city, your job is to determine the maximum load of the <i>Godzilla V12</i> so that there still exists a path between the two specified cities.</p>

### 입력 

 <p> </p>

<p>The input file will contain one or more test cases. The first line of each test case will contain two integers: the number of cities <i>n (2 ≤ n ≤ 200)</i> and the number of road segments <i>r (1 ≤ r ≤ 19900)</i> making up the street network.</p>

<p>Then <i>r</i> lines will follow, each one describing one road segment by naming the two cities connected by the segment and giving the weight limit for trucks that use this segment. Names are not longer than 30 characters and do not contain white-space characters. Weight limits are integers in the range 0 - 10000. Roads can always be travelled in both directions.</p>

<p>The last line of the test case contains two city names: start and destination.</p>

<p>Input will be terminated by two values of 0 for <i>n</i> and <i>r</i>.</p>

### 출력 

 <p>For each test case, print three lines:</p>

<ul>
	<li>a line saying "Scenario #<em>x</em>" where <em>x</em> is the number of the test case</li>
	<li>a line saying "<em>y</em> tons" where <em>y</em> is the maximum possible load</li>
	<li>a blank line</li>
</ul>

