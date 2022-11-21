import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Ore{
		int row, col, s;
		boolean visited;

		public Ore(int row, int col, int s) {
			this.row = row;
			this.col = col;
			this.s = s;
		}
	}
	static int N, M, K;
	static Ore[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new Ore[N][M];
		PriorityQueue<Ore> pq = new PriorityQueue<>((a, b) -> a.s - b.s);
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = new Ore(i, j, Integer.parseInt(st.nextToken()));
				if (i == 0 || j == 0 || j == M - 1) {
					pq.add(map[i][j]);
					map[i][j].visited = true;
				}
			}
		}
		int D = 0;
		int count = 0;
		while(count++ < K) {
			Ore now = pq.poll();
			D = Math.max(D, now.s);
			for(int d = 0; d < 4; d++) {
				int nextRow = now.row + dr[d];
				int nextCol = now.col + dc[d];
				if (!boundaryCheck(nextRow, nextCol)) {
					continue;
				}
				if (map[nextRow][nextCol].visited) {
					continue;
				}
				map[nextRow][nextCol].visited = true;
				pq.add(map[nextRow][nextCol]);
			}
		}
		System.out.println(D);
	}
	public static boolean boundaryCheck(int row, int col) {
		return 0 <= row && row < N && 0 <= col && col < M;
	}
}