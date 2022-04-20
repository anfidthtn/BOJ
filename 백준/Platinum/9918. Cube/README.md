# [Platinum V] Cube - 9918 

[문제 링크](https://www.acmicpc.net/problem/9918) 

### 성능 요약

메모리: 11548 KB, 시간: 76 ms

### 분류

너비 우선 탐색(bfs), 많은 조건 분기(case_work), 깊이 우선 탐색(dfs), 기하학(geometry), 3차원 기하학(geometry_3d), 그래프 이론(graphs), 그래프 탐색(graph_traversal), 구현(implementation)

### 문제 설명

<p>Folding six squares connected in some special ways can form a cube.  For example, in the diagram below, the six squares on the left can be folded into a cube (with face 1 opposite face 4, face 2 opposite face 6, and face 3 opposite face 5) but the six squares on the right cannot be folded into a cube (faces 3 and 5 overlap).</p>

<table class="table table-bordered td-center">
	<tbody>
		<tr>
			<td><img alt="" src="" style="width: 133px; height: 100px;"></td>
			<td><img alt="" src="" style="width: 133px; height: 100px;"></td>
		</tr>
	</tbody>
</table>

<p>A 6 × 6 array is used to represent the six squares.  Only 6 of the 36 cells are used to represent the 6 squares.  A cell representing a square contains the number for the square.   Other cells contain the number 0.  Note that the same 6 squares can be represented in many ways.  For example, the invalid six squares of the above example can be represented as follows (the one on the right is obtained after a 90 degree rotation of the one on the left): </p>

<table class="table table-bordered td-center">
	<tbody>
		<tr>
			<td>
			<pre>0 0 0 0 0 0
0 3 0 5 0 0
1 2 4 6 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0</pre>
			</td>
			<td>
			<pre>0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 5 6
0 0 0 0 0 4
0 0 0 0 3 2
0 0 0 0 0 1</pre>
			</td>
		</tr>
	</tbody>
</table>

<p>Given a square representation, determine if the squares can be folded into a cube; if so, find the face opposite face 1.</p>

### 입력 

 <p>The input consists of six lines with each line containing six integers.  All but six of the input integers are zeros.  The non-zero integers are 1, 2, 3, 4, 5, 6.</p>

### 출력 

 <p>The output consists of a single integer.  The integer is 0 if the squares cannot be folded into a cube; otherwise, the integer is the number of the face opposite face 1.</p>

