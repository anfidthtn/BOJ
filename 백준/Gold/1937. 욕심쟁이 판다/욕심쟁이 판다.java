import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final int MAXNUM = 1_000_002;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static class Point{
		int row;
		int col;
		int value;
		public Point(int row, int col, int value) {
			super();
			this.row = row;
			this.col = col;
			this.value = value;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N + 2][N + 2];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i <= N + 1; i++) {
			map[i][0] = MAXNUM;
			map[i][N + 1] = MAXNUM;			
		}
		for(int j = 0; j <= N + 1; j++) {
			map[0][j] = MAXNUM;
			map[N + 1][j] = MAXNUM;			
		}
		
		int[][] maxMove = new int[N + 2][N + 2];
		int max = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				max = Math.max(max, getMaxMove(map, maxMove, i, j));
			}
		}
		System.out.println(max);
	}
	public static int getMaxMove(int[][] map, int[][] maxMove, int row, int col) {
		if (maxMove[row][col] > 0) {
			return maxMove[row][col];
		}
		if (map[row][col] == MAXNUM) {
			return 0;
		}
		maxMove[row][col] = 1;
		PriorityQueue<Point> nextRCs = new PriorityQueue<>(new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				if (o1.value - o2.value != 0) {
					return o1.value - o2.value;
				}
				if (o1.row - o2.row != 0) {
					return o1.row - o2.row;
				}
				return o1.col - o2.col;
			}
		});
		for(int d = 0; d < 4; d++) {
			if (map[row][col] > map[row + dr[d]][col + dc[d]]) {
				nextRCs.add(new Point(row + dr[d], col + dc[d], map[row + dr[d]][col + dc[d]]));
			}
		}
		while(!nextRCs.isEmpty()) {
			Point next = nextRCs.poll();
			maxMove[row][col] = Math.max(maxMove[row][col], getMaxMove(map, maxMove, next.row, next.col) + 1);
		}
		return maxMove[row][col];
	}
}