import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			boolean[][] map = new boolean[N + 2][N + 2];
			for(int i = 0 ; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
			}
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int dir = -1;
			if (row == 0) {
				dir = 1;
			}
			else if (row == N + 1) {
				dir = 3;
			}
			else if (col == 0) {
				dir = 0;
			}
			else {
				dir = 2;
			}
			int nextRow = row + dr[dir];
			int nextCol = col + dc[dir];
			while(1 <= nextRow && nextRow <= N && 1 <= nextCol && nextCol <= N) {
				if (map[nextRow][nextCol]) {
					dir = (dir + 1) % 4;
				}
				row = nextRow;
				col = nextCol;
				nextRow = row + dr[dir];
				nextCol = col + dc[dir];
			}
			sb.append(nextRow).append(" ").append(nextCol).append("\n");
		}
		System.out.print(sb.toString());
	}
}