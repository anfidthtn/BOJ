import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Point{
		int row, col;
		boolean moveable;
		boolean light;
		boolean visited;
		List<Point> nextLights;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
			this.nextLights = new ArrayList<>();
		}
	}
	static Point[][] points;
	static int N, M;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static int count;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		points = new Point[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				points[i][j] = new Point(i, j);
			}
		}
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			points[a][b].nextLights.add(points[c][d]);
		}
		points[1][1].light = true;
		points[1][1].moveable = true;
		count = 1;
		specialDfs(points[1][1]);
		System.out.println(count);
	}
	public static void specialDfs(Point now) {
		now.visited = true;
		for(int d = 0; d < 4; d++) {
			int nextRow = now.row + dr[d];
			int nextCol = now.col + dc[d];
			if (!boundaryCheck(nextRow, nextCol)) {
				continue;
			}
			if (points[nextRow][nextCol].visited) {
				continue;
			}
			points[nextRow][nextCol].moveable = true;
			if (points[nextRow][nextCol].light) {
				specialDfs(points[nextRow][nextCol]);
			}
		}
		for (Point next : now.nextLights) {
			if (next.visited) {
				continue;
			}
			if (next.light) {
				continue;
			}
			next.light = true;
			count++;
			if (next.moveable) {
				specialDfs(next);
			}
		}
	}
	public static boolean boundaryCheck(int row, int col) {
		return 1 <= row && row <= N && 1 <= col && col <= N;
	}
}