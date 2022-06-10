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
			this.row = row;
			this.col = col;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		List<Point>[][] map = new ArrayList[R][C];
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				map[i][j] = new ArrayList<>();
				String cellss = st.nextToken();
				if (cellss.length() == 1) {
					continue;
				}
				String[] cells = cellss.split("\\+");
				for(String cell : cells) {
					map[i][j].add(new Point(getRow(cell), getCol(cell)));
				}
			}
		}
		
		boolean[][] cycleVisited = new boolean[R][C];
		int[][] visitNum = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if (cycleVisited[i][j]) {
					continue;
				}
				if(dfs(map, i, j, 1, cycleVisited, visitNum)) {
					System.out.println("yes");
					return;
				}
			}
		}
		System.out.println("no");
	}
	public static boolean dfs(List<Point>[][] map, int row, int col, int depth, boolean[][] cycleVisited, int[][] visitNum) {
		visitNum[row][col] = depth;
		cycleVisited[row][col] = true;
		for(Point next : map[row][col]) {
			if(visitNum[next.row][next.col] > 0) {
				return true;
			}
			if(cycleVisited[next.row][next.col]) {
				continue;
			}
			if (dfs(map, next.row, next.col, depth + 1, cycleVisited, visitNum)) {
				return true;
			}
		}
		visitNum[row][col] = 0;
		return false;
	}
	public static int getRow(String cell) {
		int mul = 1;
		int result = -1;
		for(int i = cell.length() - 1; '0' <= cell.charAt(i) && cell.charAt(i) <= '9'; i--) {
			result += (cell.charAt(i) - '0') * mul;
			mul *= 10;
		}
		return result;
	}
	public static int getCol(String cell) {
		int result = 0;
		for(int i = 0; 'A' <= cell.charAt(i) && cell.charAt(i) <= 'Z'; i++) {
			result *= 26;
			result += cell.charAt(i) - 'A' + 1;
		}
		result--;
		return result;
	}
}