import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		public int getDistance(int x, int y) {
			return Math.abs(this.x - x) + Math.abs(this.y - y);
		}
		public int getDistance(Point p) {
			return getDistance(p.x, p.y);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int N = Integer.parseInt(br.readLine());
			Point[] points = new Point[N + 2];
			StringTokenizer st;
			for(int i = 0; i < N + 2; i++) {
				st = new StringTokenizer(br.readLine());
				points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			boolean[] visited = new boolean[N + 2];
			visited[0] = true;
			if (dfs(points, visited, 0)) {
				sb.append("happy\n");
			}
			else {
				sb.append("sad\n");
			}
			
		}
		System.out.print(sb.toString());
	}
	public static boolean dfs (Point[] points, boolean[] visited, int now) {
		for(int i = 0; i < points.length; i++) {
			if (visited[i])
				continue;
			if (points[now].getDistance(points[i]) > 1000)
				continue;
			visited[i] = true;
			if (i == points.length - 1)
				return true;
			if (dfs(points, visited, i))
				return true;
		}
		return false;
	}
}