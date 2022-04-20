import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			if (N == 0) break;
			
			int[] dr = {-1, 0, 1, 0, -1, -1, 1, 1};
			int[] dc = {0, -1, 0, 1, -1, 1, 1, -1};
			
			char[][] map = new char[N + 2][M + 2];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= M; j++) {
					map[i][j] = (char) Integer.parseInt(st.nextToken());
				}
			}
			int count = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (map[i][j] == 1) {
						count++;
						dfs(map, dr, dc, i, j);
					}
				}
			}
			sb.append(count);
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
	public static void dfs(char[][] map, int[] dr, int[] dc, int row, int col) {
		for(int d = 0; d < 8; d++) {
			if (map[row + dr[d]][col + dc[d]] == 1) {
				map[row + dr[d]][col + dc[d]] = 0;
				dfs(map, dr, dc, row + dr[d], col + dc[d]);
			}
		}
	}
}