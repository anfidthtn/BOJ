import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int r, c, data;

		public Point(int r, int c, int data) {
			super();
			this.r = r;
			this.c = c;
			this.data = data;
		}
	}

	static Point[][] map;
	static int C, R;

	static int[][] dr = { { -1, 0, 1 }, { -1, -1, 0, 1, 1 } };
	static int[][] dc = { { 1, 1, 1 }, { -1, 0, -1, -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		Queue<Point>[] queues = new LinkedList[2];
		queues[0] = new LinkedList<>();
		queues[1] = new LinkedList<>();
		int time = 0;
		boolean[][] visited = new boolean[R + 2][C + 2];

		map = new Point[R + 2][C + 2];
		for (int i = 1; i <= R; i++) {
			String str = br.readLine();
			for (int j = 1; j <= C; j++) {
				map[i][j] = new Point(i, j, str.charAt(j - 1));
				if (map[i][j].data == 'K') {
					queues[0].add(map[i][j]);
					visited[i][j] = true;
				}
			}
		}
		while (!queues[time & 1].isEmpty()) {
			while (!queues[time & 1].isEmpty()) {
				Point now = queues[time & 1].poll();
				if (now.data == '*') {
					System.out.print(time);
					return;
				}
				for (int move = 0; move < 2; move++) {
					for (int d = 0; d < dr[move].length; d++) {
						int nextRow = now.r + dr[move][d];
						int nextCol = now.c + dc[move][d];
						if (map[nextRow][nextCol] == null) {
							continue;
						}
						if (visited[nextRow][nextCol]) {
							continue;
						}
						visited[nextRow][nextCol] = true;
						if (map[nextRow][nextCol].data == '#') {
							continue;
						}
						queues[(time + move) & 1].add(map[nextRow][nextCol]);
					}
				}
			}
			time++;
		}
		System.out.print(-1);
	}
}