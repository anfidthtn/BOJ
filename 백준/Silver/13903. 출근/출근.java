import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R;
	static int C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		int D = Integer.parseInt(br.readLine());
		int[] dr = new int[D];
		int[] dc = new int[D];
		for (int d = 0; d < D; d++) {
			st = new StringTokenizer(br.readLine());
			dr[d] = Integer.parseInt(st.nextToken());
			dc[d] = Integer.parseInt(st.nextToken());
		}

		Queue<int[]> points = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		for (int j = 0; j < C; j++) {
			if (map[0][j] == '1') {
				visited[0][j] = true;
				points.add(new int[] { 0, j });
			}
		}
		if (points.isEmpty()) {
			System.out.println(-1);
			return;
		}
		if (R == 1) {
			System.out.println(0);
			return;
		}

		int count = 0;
		while (!points.isEmpty()) {
			count++;
			int qSize = points.size();
			for (int q = 0; q < qSize; q++) {
				int[] now = points.poll();
				for (int d = 0; d < D; d++) {
					int nextRow = now[0] + dr[d];
					int nextCol = now[1] + dc[d];
					if (!boundaryCheck(nextRow, nextCol)) {
						continue;
					}
					if (map[nextRow][nextCol] == '0') {
						continue;
					}
					if (nextRow == R - 1) {
						System.out.println(count);
						return;
					}
					if (visited[nextRow][nextCol]) {
						continue;
					}
					visited[nextRow][nextCol] = true;
					points.add(new int[] { nextRow, nextCol });
				}
			}
		}
		System.out.println(-1);
	}

	public static boolean boundaryCheck(int row, int col) {
		return 0 <= row && row < R && 0 <= col && col < C;
	}
}