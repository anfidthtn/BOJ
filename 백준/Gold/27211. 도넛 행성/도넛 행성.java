import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };
	static int R, C;
	static int[][][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C][];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = new int[] { Integer.parseInt(st.nextToken()), i, j };
			}
		}
		int ans = 0;
		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j][0] == 0) {
					ans++;
					queue.add(map[i][j]);
					map[i][j][0] = 1;
					while (!queue.isEmpty()) {
						int[] now = queue.poll();
						for (int d = 0; d < 4; d++) {
							int nextRow = (R + now[1] + dr[d]) % R;
							int nextCol = (C + now[2] + dc[d]) % C;
							if (map[nextRow][nextCol][0] == 0) {
								queue.add(map[nextRow][nextCol]);
								map[nextRow][nextCol][0] = 1;
							}
						}
					}
				}
			}
		}
		System.out.print(ans);
	}
}