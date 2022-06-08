import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, -1, 0, 1 };

	static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			char[][] map = new char[R + 2][C + 1];
			Queue<Point> queue = new LinkedList<>();
			boolean[][] visited = new boolean[R + 2][C + 2];
			for (int i = 1; i <= R; i++) {
				String str = br.readLine();
				for (int j = 1; j <= C; j++) {
					map[i][j] = str.charAt(j - 1);
					if (map[i][j] == 'S') {
						visited[i][j] = true;
						queue.add(new Point(i, j));
					}
				}
			}
			
			int step = 0;
			boolean found = false;
			while(!queue.isEmpty() && !found) {
				step++;
				int qSize = queue.size();
				for(int i = 0; i < qSize && !found; i++) {
					Point now = queue.poll();
					for(int d = 0; d < 4; d++) {
						int nextRow = now.row + dr[d];
						int nextCol = now.col + dc[d];
						if (map[nextRow][nextCol] == 'G') {
							found = true;
							sb.append("Shortest Path: ").append(step).append("\n");
							break;
						}
						if (visited[nextRow][nextCol]) {
							continue;
						}
						visited[nextRow][nextCol] = true;
						if (map[nextRow][nextCol] == '0' || map[nextRow][nextCol] == 'O') {
							queue.add(new Point(nextRow, nextCol));
						}
					}
				}
			}
			if (!found) {
				sb.append("No Exit\n");
			}
		}
		System.out.print(sb.toString());
	}
}