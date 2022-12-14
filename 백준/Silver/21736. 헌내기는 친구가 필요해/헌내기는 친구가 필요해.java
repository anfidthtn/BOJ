import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static char[][] map;
	static boolean[][] visited;
	static int answer;
	static int R, C;
	static int sR, sC;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][];
		visited = new boolean[R][C];
		answer = 0;
		sR = -1;
		sC = -1;
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
			if (sR == -1) {
				for (int j = 0; j < C && sR == -1; j++) {
					if (map[i][j] == 'I') {
						sR = i;
						sC = j;
					}
				}
			}
		}
		dfs(sR, sC);
		System.out.print(answer == 0 ? "TT" : answer);
	}

	public static void dfs(int row, int col) {
		if (row < 0 || R <= row || col < 0 || C <= col) {
			return;
		}
		if (map[row][col] == 'X') {
			return;
		}
		if (visited[row][col]) {
			return;
		}
		visited[row][col] = true;
		if (map[row][col] == 'P') {
			answer++;
		}
		for (int d = 0; d < 4; d++) {
			dfs(row + dr[d], col + dc[d]);
		}
	}
}