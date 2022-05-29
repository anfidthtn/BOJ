import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[] dr = {-1, -1, 0, 1, 1, 0};
	static int[] dc = {0, 1, 1, 0, -1, -1};
	
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int N = Integer.parseInt(br.readLine());
			// 맵 입력
			char[][] map = new char[N + 2][N + 2];
			for (int i = 1; i <= N; i++) {
				String str = br.readLine();
				for (int j = 1; j <= N; j++) {
					map[i][j] = str.charAt(j - 1);
				}
			}

			boolean[][] visited = new boolean[N + 2][N + 2];
			int win = 0;
			for (int i = 1; i <= N && win == 0; i++) {
				for (int j = 1; j <= N && win == 0; j++) {
					if (!visited[i][j] && map[i][j] != '.') {
						int subResult = getSubResult(map, visited, N, i, j);
						if (subResult == 3) {
							win = 1;
						}
						if (subResult == 12) {
							win = 2;
						}
					}
				}
			}
			if (win == 1) {
				sb.append("Black wins\n");
			}
			else if (win == 2) {
				sb.append("White wins\n");
			}
			else {
				sb.append("Not finished\n");
			}
		}
		System.out.print(sb.toString());
	}
	public static int getSubResult(char[][] map, boolean[][] visited, int N, int row, int col) {
		int subResult = 0;
		char color = map[row][col];
		Queue<Point> points = new LinkedList<>();
		points.add(new Point(row, col));
		visited[row][col] = true;
		while(!points.isEmpty()) {
			Point nowPoint = points.poll();
			int nowRow = nowPoint.x;
			int nowCol = nowPoint.y;
			if (color == 'B') {
				if (nowRow == 1) {
					subResult |= 1;
				}
				if (nowRow == N) {
					subResult |= 2;
				}
			}
			else {
				if (nowCol == 1) {
					subResult |= 4;
				}
				if (nowCol == N) {
					subResult |= 8;
				}
			}
			if ((subResult & 3) == 3) {
				return 3;
			}
			if ((subResult & 12) == 12) {
				return 12;
			}
			for(int d = 0; d < 6; d++) {
				int nextRow = nowRow + dr[d];
				int nextCol = nowCol + dc[d];
				if(map[nextRow][nextCol] != color) {
					continue;
				}
				if(visited[nextRow][nextCol]) {
					continue;
				}
				visited[nextRow][nextCol] = true;
				points.add(new Point(nextRow, nextCol));
			}
		}
		return subResult;
	}
}