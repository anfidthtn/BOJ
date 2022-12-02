import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int row, col;
		char data;
		int[] times;
		int visited;

		public Point(int row, int col, char data) {
			this.row = row;
			this.col = col;
			this.data = data;
			this.times = new int[4];
			for (int i = 0; i < 4; i++) {
				times[i] = 1 << 20;
			}
			visited = 1 << 20;
		}
	}

	static Point[][] map;
	static int R, C, K;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static int sR, sC, fR, fC;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new Point[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = new Point(i, j, str.charAt(j));
			}
		}
		st = new StringTokenizer(br.readLine());
		sR = Integer.parseInt(st.nextToken()) - 1;
		sC = Integer.parseInt(st.nextToken()) - 1;
		fR = Integer.parseInt(st.nextToken()) - 1;
		fC = Integer.parseInt(st.nextToken()) - 1;
		if (sR == fR && sC == fC) {
			System.out.println(0);
			return;
		}
		Queue<Point> queue = new LinkedList<>();
		int time = 0;
		queue.add(map[sR][sC]);
		for (int i = 0; i < 4; i++) {
			map[sR][sC].times[i] = 0;
		}
		map[sR][sC].visited = 0;
		while (!queue.isEmpty()) {
			time++;
			int qSize = queue.size();
			for (int q = 0; q < qSize; q++) {
				Point now = queue.poll();
				for (int d = 0; d < 4; d++) {
					for (int k = 1; k <= K; k++) {
						int nextRow = now.row + dr[d] * k;
						int nextCol = now.col + dc[d] * k;
						if (!boundaryCheck(nextRow, nextCol)) {
							break;
						}
						if (map[nextRow][nextCol].data == '#') {
							break;
						}
						if (map[nextRow][nextCol].times[d] < time) {
							break;
						}
						if (map[nextRow][nextCol].visited < time) {
							break;
						}
						map[nextRow][nextCol].times[d] = time;
						if (nextRow == fR && nextCol == fC) {
							System.out.println(time);
							return;
						}
						if (map[nextRow][nextCol].visited > time) {
							queue.add(map[nextRow][nextCol]);
							map[nextRow][nextCol].visited = time;
						}
					}
				}
			}
		}
		System.out.println(-1);
	}

	public static boolean boundaryCheck(int row, int col) {
		return 0 <= row && row < R && 0 <= col && col < C;
	}
}