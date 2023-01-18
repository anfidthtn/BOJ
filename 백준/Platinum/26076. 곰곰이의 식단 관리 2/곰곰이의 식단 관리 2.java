import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final int NULL = 2, WALL = 1, EMPTY = 0;

	static class Point {
		int data, row, col;

		public Point(int data, int row, int col) {
			super();
			this.data = data;
			this.row = row;
			this.col = col;
		}
	}

	static int R, C;
	static Point[][] points;

	static int[] dr = { 1, 0, -1, 0, 1, -1, 1, -1 };
	static int[] dc = { 0, 1, 0, -1, 1, 1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		points = new Point[R + 2][C + 2];
		for (int i = 0; i <= R + 1; i++) {
			points[i][0] = new Point(WALL, i, 0);
			points[i][C + 1] = new Point(WALL, i, C + 1);
		}
		for (int i = 0; i <= C + 1; i++) {
			points[0][i] = new Point(WALL, 0, i);
			points[R + 1][i] = new Point(WALL, R + 1, i);
		}
		points[0][0].data = NULL;
		points[1][0].data = NULL;
		points[0][1].data = NULL;
		points[R + 1][C + 1].data = NULL;
		points[R][C + 1].data = NULL;
		points[R + 1][C].data = NULL;
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				points[i][j] = new Point(Integer.parseInt(st.nextToken()), i, j);
			}
		}
		points[1][1].data = NULL;
		points[R][C].data = NULL;
		if (R == 2 && C == 2) {
			System.out.print(2 - points[1][2].data - points[2][1].data);
			return;
		}

		Queue<Point> nowQueue = new LinkedList<>(), nextQueue = new LinkedList<>(), tempQueue = null;
		boolean[][] visited = new boolean[R + 2][C + 2];
		visited[0][2] = true;
		nowQueue.add(points[0][2]);
		int counts = -1;
		while (!nowQueue.isEmpty() && counts < 2) {
			counts++;
			while (!nowQueue.isEmpty()) {
				Point now = nowQueue.poll();
				for (int d = 0; d < 8; d++) {
					int nextRow = now.row + dr[d];
					int nextCol = now.col + dc[d];
					if (!boundaryCheck(nextRow, nextCol)) {
						continue;
					}
					if (visited[nextRow][nextCol]) {
						continue;
					}
					visited[nextRow][nextCol] = true;
					if (points[nextRow][nextCol].data == EMPTY) {
						nextQueue.add(points[nextRow][nextCol]);
					} else if (points[nextRow][nextCol].data == WALL) {
						nowQueue.add(points[nextRow][nextCol]);
					}
				}
			}
			if (visited[2][0]) {
				break;
			}
			tempQueue = nextQueue;
			nextQueue = nowQueue;
			nowQueue = tempQueue;
		}
		System.out.print(counts);
	}

	public static boolean boundaryCheck(int row, int col) {
		return 0 <= row && row <= R + 1 && 0 <= col && col <= C + 1;
	}
}