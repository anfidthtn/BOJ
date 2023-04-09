import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] dr = { 1, 0, -1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int Target = Integer.parseInt(br.readLine());
		int tRow = -1;
		int tCol = -1;
		int[][] map = new int[N + 2][N + 2];
		map[0][N] = -1;
		map[N + 1][1] = -1;
		map[N][N + 1] = -1;
		int next = 1;
		int nowRow = 1;
		int nowCol = 1;
		int d = 0;
		while (next <= N * N) {
			map[nowRow][nowCol] = next;
			if (N * N - next + 1 == Target) {
				tRow = nowRow;
				tCol = nowCol;
			}
			if (map[nowRow + dr[d]][nowCol + dc[d]] != 0) {
				d++;
				d %= 4;
			}
			nowRow += dr[d];
			nowCol += dc[d];
			next++;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append(N * N - map[i][j] + 1).append(" ");
			}
			sb.append("\n");
		}
		sb.append(tRow).append(" ").append(tCol);
		System.out.print(sb);
	}
}