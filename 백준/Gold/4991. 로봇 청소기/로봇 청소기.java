import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static int R, C;
	static int[][] dist;
	static boolean[][] visited;
	static Point[][] points;
	static List<Point> RDust;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	static int ans;

	static class Point {
		int r, c;
		char data;

		public Point(int r, int c, char data) {
			super();
			this.r = r;
			this.c = c;
			this.data = data;
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			if (R + C == 0) {
				break;
			}
			sb.append(solve()).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static int solve() throws IOException {
		points = new Point[R + 2][C + 2];
		RDust = new ArrayList<>();
		for (int i = 1; i <= R; i++) {
			String str = br.readLine();
			for (int j = 1; j <= C; j++) {
				points[i][j] = new Point(i, j, str.charAt(j - 1));
				if (points[i][j].data == 'o') {
					RDust.add(0, points[i][j]);
				} else if (points[i][j].data == '*') {
					RDust.add(points[i][j]);
				}
			}
		}
		if (RDust.size() == 1) {
			return 0;
		}
		dist = new int[RDust.size()][RDust.size()];
		for (int i = 0; i < RDust.size(); i++) {
			RDust.get(i).data = (char) (i + 'a');
			Arrays.fill(dist[i], 1 << 20);
		}
		for (int i = 0; i < RDust.size(); i++) {
			visited = new boolean[R + 2][C + 2];
			Queue<Point> queue = new LinkedList<>();
			queue.add(RDust.get(i));
			visited[RDust.get(i).r][RDust.get(i).c] = true;
			int nowd = 0;
			while (!queue.isEmpty()) {
				int qsize = queue.size();
				for (int q = 0; q < qsize; q++) {
					Point now = queue.poll();
					if ('a' <= now.data && now.data < 'a' + RDust.size()) {
						dist[i][now.data - 'a'] = nowd;
					}
					for (int d = 0; d < 4; d++) {
						int nextRow = now.r + dr[d];
						int nextCol = now.c + dc[d];
						if (points[nextRow][nextCol] == null) {
							continue;
						}
						if (points[nextRow][nextCol].data == 'x') {
							continue;
						}
						if (visited[nextRow][nextCol]) {
							continue;
						}
						visited[nextRow][nextCol] = true;
						queue.add(points[nextRow][nextCol]);
					}
				}
				nowd++;
			}
		}
		ans = 1 << 21;
		for (int i = 1; i < RDust.size(); i++) {
			check(1, 0, 1, 0);
		}
		if (ans >= 1 << 20) {
			return -1;
		}
		return ans;
	}

	public static void check(int visit, int before, int count, int cost) {
		if (count == RDust.size()) {
			ans = Math.min(ans, cost);
			return;
		}
		for (int i = 1; i < RDust.size(); i++) {
			if ((visit & (1 << i)) != 0) {
				continue;
			}
			check(visit | (1 << i), i, count + 1, cost + dist[before][i]);
		}
	}
}