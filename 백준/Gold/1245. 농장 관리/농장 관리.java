import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int dr[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int dc[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static int[][] map;
	static boolean[][] visited;
	static boolean check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N + 2][M + 2];
		boolean allZero = true;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) + 1;
				if (map[i][j] > 1) {
					allZero = false;
				}
			}
		}
		if (allZero) {
			System.out.println(0);
			return;
		}

		visited = new boolean[N + 2][M + 2];

		int count = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (!visited[i][j]) {
					// 탐색을 돌린다.
					visited[i][j] = true;
					check = true;
					dfs(i, j, map[i][j]);
					if (check) {
						// 더 높은 덩어리가 없으면 카운트를 더해줍니다.
						count++;
					}
				}
			}
		}
		System.out.println(count);
	}

	public static void dfs(int row, int col, int value) {
		for (int d = 0; d < 8; d++) {
			int nextRow = row + dr[d];
			int nextCol = col + dc[d];
			if (map[nextRow][nextCol] > value) {
				// 이 덩어리 주변에는 더 높은 덩어리가 있습니다.
				check = false;
			}
			if (map[nextRow][nextCol] != value) {
				continue;
			}
			if (visited[nextRow][nextCol]) {
				continue;
			}
			visited[nextRow][nextCol] = true;
			dfs(nextRow, nextCol, value);
		}
	}
}