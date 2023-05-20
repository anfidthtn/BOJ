# [Gold III] if 3 - 15551 

[문제 링크](https://www.acmicpc.net/problem/15551) 

### 성능 요약

메모리: 11512 KB, 시간: 76 ms

### 분류

해싱

### 문제 설명

<p>다음 프로그램을 실행시켰을 때, "<code>true</code>"를 출력하는 길이가 N인 문자열 <code>a, b</code> 를 찾는 프로그램을 작성하시오.</p>

<div><div id="highlighter_636248" class="syntaxhighlighter  java"><table border="0" cellpadding="0" cellspacing="0"><tbody><tr><td class="gutter"><div class="line number1 index0 alt2">1</div><div class="line number2 index1 alt1">2</div><div class="line number3 index2 alt2">3</div><div class="line number4 index3 alt1">4</div><div class="line number5 index4 alt2">5</div><div class="line number6 index5 alt1">6</div><div class="line number7 index6 alt2">7</div><div class="line number8 index7 alt1">8</div><div class="line number9 index8 alt2">9</div><div class="line number10 index9 alt1">10</div><div class="line number11 index10 alt2">11</div><div class="line number12 index11 alt1">12</div><div class="line number13 index12 alt2">13</div><div class="line number14 index13 alt1">14</div></td><td class="code"><div class="container"><div class="line number1 index0 alt2"><code class="java keyword">import</code> <code class="java plain">java.util.*;</code></div><div class="line number2 index1 alt1"> </div><div class="line number3 index2 alt2"><code class="java keyword">public</code> <code class="java keyword">class</code> <code class="java plain">Main {</code></div><div class="line number4 index3 alt1"><code class="java spaces">    </code><code class="java keyword">public</code> <code class="java keyword">static</code> <code class="java keyword">void</code> <code class="java plain">main(String args[]) {</code></div><div class="line number5 index4 alt2"><code class="java spaces">        </code><code class="java plain">Scanner sc = </code><code class="java keyword">new</code> <code class="java plain">Scanner(System.in);</code></div><div class="line number6 index5 alt1"><code class="java spaces">        </code><code class="java plain">String a = sc.next();</code></div><div class="line number7 index6 alt2"><code class="java spaces">        </code><code class="java plain">String b = sc.next();</code></div><div class="line number8 index7 alt1"><code class="java spaces">        </code><code class="java keyword">if</code> <code class="java plain">(!a.equals(b) && a.hashCode() == b.hashCode()) {</code></div><div class="line number9 index8 alt2"><code class="java spaces">            </code><code class="java plain">System.out.println(</code><code class="java string">"true"</code><code class="java plain">);</code></div><div class="line number10 index9 alt1"><code class="java spaces">        </code><code class="java plain">} </code><code class="java keyword">else</code> <code class="java plain">{</code></div><div class="line number11 index10 alt2"><code class="java spaces">            </code><code class="java plain">System.out.println(</code><code class="java string">"false"</code><code class="java plain">);</code></div><div class="line number12 index11 alt1"><code class="java spaces">        </code><code class="java plain">}</code></div><div class="line number13 index12 alt2"><code class="java spaces">    </code><code class="java plain">}</code></div><div class="line number14 index13 alt1"><code class="java plain">}</code></div></div></td></tr></tbody></table></div></div>

<p>실행 환경은 BOJ 채점 서버의 Java 실행 환경과 같다.</p>

### 입력 

 <p>첫째 줄에 문자열의 길이 N (2 ≤ N ≤ 100)이 주어진다.</p>

### 출력 

 <p>첫째 줄에 문자열 <code>a</code>, 둘째 줄에 문자열 <code>b</code>를 출력한다.</p>

<p>문자열 <code>a</code>와 <code>b</code>는 <code>java.util.Scanner</code>의 <code>next</code>메소드로 입력받을 수 있어야 한다.</p>

