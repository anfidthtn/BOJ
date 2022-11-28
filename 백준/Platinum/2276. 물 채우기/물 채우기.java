import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int row, col, height;
		boolean filled;

		public Node(int row, int col, int height, boolean filled) {
			this.row = row;
			this.col = col;
			this.height = height;
			this.filled = filled;
		}
	}

	static int R, C;
	static Node[][] map;
	static long answer;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new Node[R + 2][C + 2];
		answer = 0;
		PriorityQueue<Node> outerNodes = new PriorityQueue<>((a, b) -> a.height - b.height);
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				map[i][j] = new Node(i, j, Integer.parseInt(st.nextToken()), false);
				if (i == 1 || i == R || j == 1 || j == C) {
					map[i][j].filled = true;
					outerNodes.add(map[i][j]);
				}
			}
		}
		while (!outerNodes.isEmpty()) {
			Node now = outerNodes.poll();
			for (int d = 0; d < 4; d++) {
				int nextRow = now.row + dr[d];
				int nextCol = now.col + dc[d];
				if (!boundaryCheck(nextRow, nextCol)) {
					continue;
				}
				if (map[nextRow][nextCol].filled) {
					continue;
				}
				map[nextRow][nextCol].filled = true;
				answer += Math.max(0, now.height - map[nextRow][nextCol].height);
				map[nextRow][nextCol].height = Math.max(now.height, map[nextRow][nextCol].height);
				outerNodes.add(map[nextRow][nextCol]);
			}
		}
		System.out.println(answer);
	}

	public static boolean boundaryCheck(int row, int col) {
		return 1 <= row && row <= R && 1 <= col && col <= C;
	}
}