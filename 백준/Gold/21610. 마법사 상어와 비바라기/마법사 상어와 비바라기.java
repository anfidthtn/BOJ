import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		// 행, 열, 물, 구름 사라진 시간
		int row, col, watar, disappear;

		public Point(int row, int col, int watar) {
			this.row = row;
			this.col = col;
			this.watar = watar;
			this.disappear = -1;
		}
	}

	static int N, M;
	static Point[][] points;
	// x, 좌, 좌상, 상, 우상, 우, 우하, 하, 좌하
	static int[] dr = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[] dc = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	// 좌상, 우상, 우하, 좌하
	static int[] ddr = { -1, -1, 1, 1 };
	static int[] ddc = { -1, 1, 1, -1 };

	// 구름의 이동 전/후 위치
	static Queue<Point> beforeClouds, afterClouds, temp;

	static int time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 모든 점 정보 담을 배열 선언
		points = new Point[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				// 위치 정보 등록
				points[i][j] = new Point(i, j, Integer.parseInt(st.nextToken()));
			}
		}
		// 구름의 이동 전 위치
		beforeClouds = new LinkedList<>();
		// 구름의 이동 후 위치
		afterClouds = new LinkedList<>();
		beforeClouds.add(points[N][1]);
		beforeClouds.add(points[N][2]);
		beforeClouds.add(points[N - 1][1]);
		beforeClouds.add(points[N - 1][2]);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			time = i;
			cloudsMove(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			rain();
			dupBug();
			makeClouds();
		}
		long ans = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				ans += points[i][j].watar;
			}
		}
		System.out.print(ans);
	}

	public static void cloudsMove(int d, int s) {
		while (!beforeClouds.isEmpty()) {
			Point cloud = beforeClouds.poll();
			afterClouds.add(
					points[((cloud.row + dr[d] * s + N * 50 - 1) % N) + 1][((cloud.col + dc[d] * s + N * 50 - 1) % N)
							+ 1]);
		}
	}

	public static void rain() {
		for (Point cloud : afterClouds) {
			cloud.watar++;
			cloud.disappear = time;
		}
	}

	public static void dupBug() {
		while (!afterClouds.isEmpty()) {
			Point cloud = afterClouds.poll();
			for (int d = 0; d < 4; d++) {
				int nextRow = cloud.row + ddr[d];
				int nextCol = cloud.col + ddc[d];
				if (!boundaryCheck(nextRow, nextCol)) {
					continue;
				}
				cloud.watar += points[nextRow][nextCol].watar > 0 ? 1 : 0;
			}
		}
	}

	public static void makeClouds() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (points[i][j].disappear == time) {
					continue;
				}
				if (points[i][j].watar >= 2) {
					points[i][j].watar -= 2;
					beforeClouds.add(points[i][j]);
				}
			}
		}
	}

	public static boolean boundaryCheck(int row, int col) {
		return 1 <= row && row <= N && 1 <= col && col <= N;
	}
}
