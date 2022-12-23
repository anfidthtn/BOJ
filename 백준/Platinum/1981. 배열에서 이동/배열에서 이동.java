import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int mustMin, mustMax;
	static boolean[][] visited;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	static class Point {
		int row, col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	static Point[][] points;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		points = new Point[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				points[i][j] = new Point(i, j);
			}
		}
		mustMin = Math.min(map[0][0], map[N - 1][N - 1]);
		mustMax = Math.max(map[0][0], map[N - 1][N - 1]);
		int answer = 200;
		for (int start = mustMin; start >= 0; start--) {
			int left = mustMax;
			int right = 200;
			while (left <= right) {
				int end = (left + right) >> 1;
				if (end - start >= answer) {
					right = end - 1;
					continue;
				}
				if (check(start, end)) {
					answer = end - start;
					right = end - 1;
				} else {
					left = end + 1;
				}
			}
		}
		System.out.print(answer);
	}

	public static boolean check(int start, int end) {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
		visited[0][0] = true;
		Queue<Point> queue = new LinkedList<>();
		queue.add(points[0][0]);
		while (!queue.isEmpty()) {
			Point now = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nextRow = now.row + dr[d];
				int nextCol = now.col + dc[d];
				if (nextRow < 0 || nextCol < 0 || N <= nextRow || N <= nextCol) {
					continue;
				}
				if (map[nextRow][nextCol] < start || end < map[nextRow][nextCol]) {
					continue;
				}
				if (visited[nextRow][nextCol]) {
					continue;
				}
				visited[nextRow][nextCol] = true;
				if (nextRow == N - 1 && nextCol == N - 1) {
					return true;
				}
				queue.add(points[nextRow][nextCol]);
			}
		}
		return false;
	}
}