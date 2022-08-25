# [Platinum V] Do You Know Your ABCs? - 21818 

[문제 링크](https://www.acmicpc.net/problem/21818) 

### 성능 요약

메모리: 38664 KB, 시간: 356 ms

### 분류

브루트포스 알고리즘(bruteforcing), 구현(implementation), 수학(math)

### 문제 설명

<p>Farmer John's cows have been holding a daily online gathering on the "mooZ" video meeting platform. For fun, they have invented a simple number game to play during the meeting to keep themselves entertained.</p>

<p>Elsie has three positive integers $A$, $B$, and $C$ ($1\le A\le B\le C$). These integers are supposed to be secret, so she will not directly reveal them to her sister Bessie. Instead, she tells Bessie $N$ ($4\le N\le 7$) distinct integers $x_1,x_2,\ldots,x_N$ ($1\le x_i\le 10^9$), claiming that each $x_i$ is one of $A$, $B$, $C$, $A+B$, $B+C$, $C+A$, or $A+B+C$. However, Elsie may be lying; the integers $x_i$ might not correspond to any valid triple $(A,B,C)$.</p>

<p>This is too hard for Bessie to wrap her head around, so it is up to you to determine the number of triples $(A,B,C)$ that are consistent with the numbers Elsie presented (possibly zero).</p>

<p>Each input file will contain $T$ ($1\le T\le 100$) test cases that should be solved independently.</p>

### 입력 

 <p>The first input line contains $T$.</p>

<p>Each test case starts with $N$, the number of integers Elsie gives to Bessie.</p>

<p>The second line of each test case contains $N$ distinct integers $x_1,x_2,\ldots,x_N$.</p>

### 출력 

 <p>For each test case, output the number of triples $(A,B,C)$ that are consistent with the numbers Elsie presented.</p>

