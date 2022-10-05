import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static char[][] map;
	static int N;
	static int M;
	static int answer;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N + 2][M + 2];
		answer = 0;
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = str.charAt(j - 1);
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (map[i][j] == 'L') {
					bfs(i, j);
				}
			}
		}
		System.out.println(answer);
	}

	public static void bfs(int row, int col) {
		boolean[][] visited = new boolean[N + 2][M + 2];
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(row, col));
		visited[row][col] = true;
		int time = -1;
		while (!queue.isEmpty()) {
			time++;
			int qSize = queue.size();
			for (int q = 0; q < qSize; q++) {
				Point now = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nextRow = now.r + dr[d];
					int nextCol = now.c + dc[d];
					if (map[nextRow][nextCol] != 'L') {
						continue;
					}
					if (visited[nextRow][nextCol]) {
						continue;
					}
					visited[nextRow][nextCol] = true;
					queue.add(new Point(nextRow, nextCol));
				}
			}
		}
		answer = Math.max(answer, time);
	}
}