# [Gold V] Hex - 5378 

[문제 링크](https://www.acmicpc.net/problem/5378) 

### 성능 요약

메모리: 23480 KB, 시간: 240 ms

### 분류

너비 우선 탐색(bfs), 깊이 우선 탐색(dfs), 그래프 이론(graphs), 그래프 탐색(graph_traversal), 구현(implementation)

### 문제 설명

<p>Hex is a game for two players, played on a diamond-shaped board with hexagonal cells. At the start of the game, all cells are empty. Each player in turn puts a stone of his own color (black or white) in any empty cell. The goal for Black is to connect the top left edge of the board with the bottom right edge, by making a path consisting of neighboring cells with Black stones in them. White attempts to make a path from the top right to the bottom left. The cells located in the corners of the diamond count as being adjacent to both corresponding edges. The player to start the game is Black. An interesting feature of this game is that there is always a winner: if all cells are filled and one player does not have a path connecting his designated edges, then the other player automatically does have one.</p>

<p><img alt="" src="https://www.acmicpc.net/upload/images2/hex.png" style="height:269px; width:466px"></p>

<p>The game situation as described by the first test case in the sample input. Black has successfully connected the top left edge and the bottom right edge and has therefore won the game.</p>

<p>Write a program which, given a game situation, determines which player has won, or that the game has not yet finished.</p>

### 입력 

 <p>On the first line one positive number: the number of test cases, at most 100. After that per test case:</p>

<ul>
	<li>one line with one integer n (2 ≤ n ≤ 100): the size of the board (the number of cells along each edge).</li>
	<li>n lines with n characters: the i-th line lists the contents of the i-th diagonal of the board from the bottom left edge to the top right edge (these diagonals are labeled 1-7 in the figure). The diagonal along the top left edge of the board (A1-G1 in the figure) is given first and the diagonal along the bottom right edge (A7-G7) is given last. On each line, the first character represents the contents of the cell along the bottom left edge (labeled A in the figure) and the last concerns the cell along the top right edge (G). Each character is ‘B’, ‘W’ or ‘.’, representing a black stone, white stone, or empty cell respectively</li>
</ul>

<p>The number of cells with black stones is equal to, or one more than, the number of cells with white stones. The game situation can be such that one of the players had already won a few turns earlier.</p>

### 출력 

 <p>Per test case:</p>

<ul>
	<li>one line with either “Black wins” or “White wins” or “Not finished”, indicating the game status.</li>
</ul>

