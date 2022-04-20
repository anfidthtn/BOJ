import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[] dr = {-1, 0, 1, 0};
			int[] dc = {0, -1, 0, 1};
			
			char[][] map = new char[M + 2][N + 2];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				map[Integer.parseInt(st.nextToken()) + 1][Integer.parseInt(st.nextToken()) + 1] = 1;
			}
			int count = 0;
			for (int i = 1; i <= M; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][j] == 1) {
						count++;
						dfs(map, dr, dc, M, N, i, j);
					}
				}
			}
			sb.append(count);
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
	public static void dfs(char[][] map, int[] dr, int[] dc, int M, int N, int row, int col) {
		for(int d = 0; d < 4; d++) {
			if (map[row + dr[d]][col + dc[d]] == 1) {
				map[row + dr[d]][col + dc[d]] = 0;
				dfs(map, dr, dc, M, N, row + dr[d], col + dc[d]);
			}
		}
	}
}