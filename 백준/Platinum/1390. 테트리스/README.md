# [Platinum II] 테트리스 - 1390 

[문제 링크](https://www.acmicpc.net/problem/1390) 

### 성능 요약

메모리: 15364 KB, 시간: 88 ms

### 분류

비트마스킹(bitmask), 많은 조건 분기(case_work), 다이나믹 프로그래밍(dp), 비트필드를 이용한 다이나믹 프로그래밍(dp_bitfield), 구현(implementation)

### 문제 설명

<p>김진영은 테트리스 국가대표이다. 국가대표이기 때문에 실력도 물론 뛰어나다. 지금까지 한번도 최백준과 강민승에게 진 적이 없으므로 김진영은 더 이상 연습할 필요가 없다고 생각했다. 이제 할 일이 없어진 김진영은 갑자기 이런 생각을 했다. 테트리스 블록을 3*N크기로 차곡차곡 쌓는 방법은 총 몇 가지가 있을까?</p>

<p>테트리스에는 다음과 같이 7개의 블록이 있다. 그러나 김진영은 테트리스를 할 때 1*4크기의 블록을 사용하지 않는다. 따라서 김진영은 테트리스에는 총 6개의 블록을 이용할 것이다. <그림 1>을 참고한다.</p>

<p style="text-align:center"><img alt="" src="https://www.acmicpc.net/upload/201004/tetetetet.PNG" style="height:78px; width:484px"></p>

<p style="text-align:center"><그림 1></p>

<p style="text-align:left">  7개의 블록은 90, 180, 270도 방향으로 회전시킬 수 있다.</p>

<p style="text-align:left">  다음은 3*4크기로 테트리스 블록을 차곡차곡 쌓을 수 없는 두 가지 예와, 쌓을 수 있는 한 가지 예이다.</p>

<p style="text-align: center;"><img alt="" src="https://www.acmicpc.net/upload/201004/tetet.PNG" style="height:124px; width:327px"></p>

<p style="text-align: center;">  <그림 2> : 김진영 조교는 1*4크기의 블록을 사용하지 않는다.<br>
  <그림 3> : 올바른 방법이다.<br>
  <그림 4> : 그림 2와 같은 이유다.</p>

### 입력 

 <p>첫째 줄에 N이 주어진다. N은 300보다 작거나 같은 자연수이다.</p>

### 출력 

 <p>첫째 줄에 테트리스 조각을 3*N크기로 쌓는 경우의 수를 1,000,000으로 나눈 나머지를 출력한다.</p>

