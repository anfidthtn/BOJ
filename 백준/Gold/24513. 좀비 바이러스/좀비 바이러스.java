import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int row, col, data, time;

		public Point(int row, int col, int data) {
			this.row = row;
			this.col = col;
			this.data = data;
			this.time = -1;
		}

	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		Point[][] map = new Point[R][C];
		Queue<Point> queue = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = new Point(i, j, Integer.parseInt(st.nextToken()));
				if (map[i][j].data > 0) {
					map[i][j].time = 0;
					queue.add(map[i][j]);
				}
			}
		}
		int time = 0;
		while (!queue.isEmpty()) {
			time++;
			int qSize = queue.size();
			for (int q = 0; q < qSize; q++) {
				Point now = queue.poll();
				if (now.data == 3) {
					continue;
				}
				for(int d = 0; d < 4; d++) {
					int nextRow = now.row + dr[d];
					int nextCol = now.col + dc[d];
					if (nextRow < 0 || nextCol < 0 || R <= nextRow || C <= nextCol) {
						continue;
					}
					Point next = map[nextRow][nextCol];
					if (next.data == -1 || next.data == 3) {
						continue;
					}
					if (next.time == -1 || next.time == time) {
						next.data |= now.data;
						if (next.time == -1) {
							next.time = time;
							queue.add(next);
						}
					}
				}
			}
		}
		int[] counts = new int[4];
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if (map[i][j].data > 0) {
					counts[map[i][j].data]++;
				}
			}
		}
		for(int i = 1; i <= 3; i++) {
			System.out.print(counts[i] + " ");
		}
	}
}