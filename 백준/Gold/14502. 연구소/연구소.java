import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static int nowSafetyZone;

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N + 2][M + 2];

		List<Point> emptyZone = new ArrayList<>();
		List<Point> virusZone = new LinkedList<>();

		int startCount = -3;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = st.nextToken().charAt(0);
				switch (map[i][j]) {
				case '0':
					emptyZone.add(new Point(i, j));
					startCount++;
					break;
				case '2':
					virusZone.add(new Point(i, j));
					break;
				}
			}
		}
		int maxSafetyZone = 0;
		for (int a = 0; a < emptyZone.size(); a++) {
			for (int b = a + 1; b < emptyZone.size(); b++) {
				for (int c = b + 1; c < emptyZone.size(); c++) {
					Point aP = emptyZone.get(a);
					Point bP = emptyZone.get(b);
					Point cP = emptyZone.get(c);
					map[aP.r][aP.c] = '1';
					map[bP.r][bP.c] = '1';
					map[cP.r][cP.c] = '1';
					boolean[][] visited = new boolean[N + 2][M + 2];
					nowSafetyZone = startCount;
					for (Point virus : virusZone) {
						visited[virus.r][virus.c] = true;
						spreadVirus(map, visited, virus.r, virus.c, maxSafetyZone);
					}
					maxSafetyZone = nowSafetyZone;
					map[aP.r][aP.c] = '0';
					map[bP.r][bP.c] = '0';
					map[cP.r][cP.c] = '0';
				}
			}
		}
		System.out.println(maxSafetyZone);
	}

	public static void spreadVirus(char[][] map, boolean[][] visited, int row, int col, int maxSafetyZone) {
		for (int d = 0; d < 4; d++) {
			if (map[row + dr[d]][col + dc[d]] != '0')
				continue;
			if (visited[row + dr[d]][col + dc[d]])
				continue;
			visited[row + dr[d]][col + dc[d]] = true;
			if (nowSafetyZone <= maxSafetyZone)
				return;
			nowSafetyZone--;
			spreadVirus(map, visited, row + dr[d], col + dc[d], maxSafetyZone);
		}
	}
}