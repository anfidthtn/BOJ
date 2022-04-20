import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
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
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[r + 2][c + 2];
		int totalCheese = 0;
		for (int i = 1; i <= r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= c; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if (map[i][j] == '1') {
					totalCheese++;
				}
			}
		}
		
		if (totalCheese == 0) {
			System.out.println(0);
			System.out.println(0);
			return;
		}
		
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		LinkedList<Point> outerAir = new LinkedList<>();
		boolean[][] visited = new boolean[r + 2][c + 2];
		visited[1][1] = true;
		outerAir.addLast(new Point(1, 1));
		int time = 0;
		int remain = totalCheese;
		while(!outerAir.isEmpty()) {
			time++;
			remain = totalCheese;
			LinkedList<Point> airsideCheese = new LinkedList<>();
			while(!outerAir.isEmpty()) {
				Point now = outerAir.pollFirst();
				for(int d = 0; d < 4; d++) {
					int nextRow = now.row + dr[d];
					int nextCol = now.col + dc[d];
					if (visited[nextRow][nextCol]) continue;
					visited[nextRow][nextCol] = true;
					if (map[nextRow][nextCol] == '0') {
						outerAir.addLast(new Point(nextRow, nextCol));
					}
					else if(map[nextRow][nextCol] == '1') {
						airsideCheese.addLast(new Point(nextRow, nextCol));
						if (--totalCheese == 0) {
							System.out.println(time);
							System.out.println(remain);
							return;
						}
					}
				}
			}
			outerAir = airsideCheese;
		}
		
	}
}