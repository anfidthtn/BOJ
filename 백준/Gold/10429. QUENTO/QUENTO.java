import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Point{
		int row;
		int col;
		public Point(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	static int N;
	static int M;
	static char[][] map;
	static List<Point>[][] link;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[5][5];
		link = new ArrayList[5][5];
		for(int i = 1; i <= 3; i++) {
			String str = br.readLine();
			for(int j = 1; j <= 3; j++) {
				map[i][j] = str.charAt(j - 1);
				link[i][j] = new ArrayList<>();
				for(int[] next : new int[][] {{i - 1, j}, {i, j - 1}, {i + 1, j}, {i, j + 1}}) {
					if (next[0] == 0 || next[0] == 4 || next[1] == 0 || next[1] == 4) {
						continue;
					}
					link[i][j].add(new Point(next[0], next[1]));
				}
			}
		}
		link[0][0] = new ArrayList<>();
		link[0][0].add(new Point(1, 1));
		link[0][0].add(new Point(3, 1));
		link[0][0].add(new Point(1, 3));
		link[0][0].add(new Point(3, 3));
		link[0][0].add(new Point(2, 2));
		boolean[][] visited = new boolean[5][5];
		dfs(new Point[2 * M - 1], 0, visited, 0, 0, 0, '+', 0);
		System.out.println(0);
	}
	public static void dfs(Point[] list, int idx, boolean[][] visited, int row, int col, int num, char oper, int numCount) {
		if (numCount == M) {
			if (num == N) {
				System.out.println(1);
				for(Point point : list) {
					System.out.println((point.row - 1) + " " + (point.col - 1));
				}
				System.exit(0);
			}
			else {
				return;
			}
		}
		for(Point next : link[row][col]) {
			if (visited[next.row][next.col]) {
				continue;
			}
			visited[next.row][next.col] = true;
			list[idx] = next;
			switch(map[next.row][next.col]) {
			case '+':
			case '-':
				dfs(list, idx + 1, visited, next.row, next.col, num, map[next.row][next.col], numCount);
				break;
			default:
				int nextNum = 0;
				switch(oper) {
				case '+':
					nextNum = num + (map[next.row][next.col] - '0'); 
					break;
				case '-':
					nextNum = num - (map[next.row][next.col] - '0'); 
					break;
				}
				dfs(list, idx + 1, visited, next.row, next.col, nextNum, oper, numCount + 1);
			}
			visited[next.row][next.col] = false;
		}
	}
}