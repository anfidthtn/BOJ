# [Platinum IV] 부모님께 큰절 하고 - 18859 

[문제 링크](https://www.acmicpc.net/problem/18859) 

### 성능 요약

메모리: 188356 KB, 시간: 412 ms

### 분류

자료 구조, 그리디 알고리즘, 정렬

### 문제 설명

<p><strong><em>Agent</em></strong> 욱제는 훈련소로 떠나기 전, 부모님께 큰절을 올렸다. 어릴 적, 욱제의 부모님은 욱제에게 말하곤 했다.</p>

<p style="text-align: center;"><em>"욱제야, 꼭 기억하렴. <strong>Agent</strong>의 큰절은 <strong>예술적</strong>이어야 한단다."</em></p>

<p>하지만, 오늘 부모님은 욱제에게 크게 실망했다. 욱제의 큰절이 예술적이지 않다고 생각했기 때문이다. 욱제의 큰절이 예술적일 조건은 다음과 같다.</p>

<p><meta charset="utf-8"></p>

<ol dir="ltr">
	<li>욱제의 큰절은 길이 N의 정수열 a<sub>1</sub>, …, a<sub>N</sub>으로 나타내어진다.</li>
	<li>욱제의 큰절이 예술적이려면, 이 정수열이 일정하게 감소했다가 일정하게 증가하는 수열이어야 한다. 즉, 인덱스 2 ≤ i ≤ N - 1가 존재해, a<sub>1</sub>, …, a<sub>i</sub>는 공차 x < 0의 등차수열이고, a<sub>i</sub>, …, a<sub>N</sub>은 공차 y > 0의 등차수열이다.</li>
</ol>

<p dir="ltr">당신은 욱제의 큰절을 나타내는 수열을 보고 욱제의 큰절이 예술적인지 평가해야 한다.</p>

<p dir="ltr">하지만 아뿔싸! 축구 선수 <a href="https://www.acmicpc.net/user/messi">메시</a>가 이 수열을 섞어버렸다. 순서 없이 섞인 수열을 보고 원래 욱제의 큰절이 예술적이었을 수 있을지 알아보자.</p>

### 입력 

 <p>첫째 줄에 수열의 길이 N이 주어진다.</p>

<p><meta charset="utf-8"></p>

<p dir="ltr">둘째 줄에 욱제의 큰절을 나타내는 수열 a<sub>1</sub>, …, a<sub>N</sub>을 순서 없이 섞은 수열이 공백을 사이에 두고 주어진다.</p>

### 출력 

 <p><meta charset="utf-8">입력 수열을 재배열하여 예술적인 큰절을 나타내는 수열을 만들 수 있으면 <code>Yes</code>를, 아니면 <code>No</code>를 출력한다.</p>

