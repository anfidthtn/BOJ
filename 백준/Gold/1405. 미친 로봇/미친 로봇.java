import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static double[] probs;
	static double total = 1.0;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		probs = new double[4];
		for (int i = 0; i < 4; i++) {
			probs[i] = Integer.parseInt(st.nextToken()) / 100.0;
		}
		boolean[][] visited = new boolean[30][30];
		visited[15][15] = true;
		dfs(visited, 0, 15, 15, 1.0);
		System.out.printf("%.10f", total);
	}

	public static void dfs(boolean[][] visited, int move, int row, int col, double value) {
		if (move == N) {
			return;
		}
		if (value == 0) {
			return;
		}
		for (int d = 0; d < 4; d++) {
			int nextRow = row + dr[d];
			int nextCol = col + dc[d];
			if (visited[nextRow][nextCol]) {
				total -= value * probs[d];
				continue;
			}
			visited[nextRow][nextCol] = true;
			dfs(visited, move + 1, nextRow, nextCol, value * probs[d]);
			visited[nextRow][nextCol] = false;
		}
	}
}