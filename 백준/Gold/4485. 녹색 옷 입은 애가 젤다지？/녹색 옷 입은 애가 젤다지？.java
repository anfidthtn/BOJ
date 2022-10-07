import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}
			int[][] map = new int[N][N];
			int[][] minDist = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					minDist[i][j] = 1 << 25;
				}
			}
			PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
			pq.add(new int[] { 0, 0, map[0][0] });
			minDist[0][0] = map[0][0];
			while (!pq.isEmpty()) {
				int[] now = pq.poll();
				if (now[0] == N - 1 && now[1] == N - 1) {
					break;
				}
				for (int d = 0; d < 4; d++) {
					int nextRow = now[0] + dr[d];
					int nextCol = now[1] + dc[d];
					if(!boundarycheck(nextRow, nextCol)) {
						continue;
					}
					int nextDist = now[2] + map[nextRow][nextCol];
					if (minDist[nextRow][nextCol] <= nextDist) {
						continue;
					}
					minDist[nextRow][nextCol] = nextDist;
					pq.add(new int[] {nextRow, nextCol, nextDist});
				}
			}
			sb.append("Problem ").append(t).append(": ").append(minDist[N - 1][N - 1]).append("\n");
			t++;
		}
		System.out.print(sb.toString());
	}

	public static boolean boundarycheck(int row, int col) {
		return 0 <= row && row < N && 0 <= col && col < N;
	}
}