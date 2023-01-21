import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static int startR = 0, startC = 0;
	static char[][] map;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static int[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'C') {
					startR = i;
					startC = j;
				}
			}
		}
		Queue<int[]> queue = new LinkedList<>();
		int count = -1;
		map[startR][startC] = '.';
		visited = new int[R][C];
		for (int i = 0; i < R; i++) {
			Arrays.fill(visited[i], 1 << 20);
		}
		visited[startR][startC] = -1;
		queue.add(new int[] { startR, startC });
		while (!queue.isEmpty()) {
			count++;
			int qSize = queue.size();
			for (int q = 0; q < qSize; q++) {
				int[] now = queue.poll();
				for (int d = 0; d < 4; d++) {
					for (int k = 1; k < R + C; k++) {
						int nextRow = now[0] + dr[d] * k;
						int nextCol = now[1] + dc[d] * k;
						if (!boundaryCheck(nextRow, nextCol)) {
							break;
						}
						if (map[nextRow][nextCol] == '*') {
							break;
						}
						if (visited[nextRow][nextCol] < count) {
							break;
						}
						if (visited[nextRow][nextCol] == count) {
							continue;
						}
						visited[nextRow][nextCol] = count;
						if (map[nextRow][nextCol] == 'C') {
							System.out.print(count);
							return;
						}
						queue.add(new int[] { nextRow, nextCol });
					}
				}
			}
		}
	}

	public static boolean boundaryCheck(int row, int col) {
		return 0 <= row && row < R && 0 <= col && col < C;
	}
}