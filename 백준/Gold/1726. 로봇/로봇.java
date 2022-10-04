import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static boolean[][][] visited;
	static int[][] map;
	static int eR, eC, eD;

	static class Node {
		int r, c, d;

		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N + 2][M + 2][4];
		map = new int[N + 2][M + 2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				if (Integer.parseInt(st.nextToken()) == 0) {
					map[i][j] = 1;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int sR = Integer.parseInt(st.nextToken());
		int sC = Integer.parseInt(st.nextToken());
		int sD = Integer.parseInt(st.nextToken()) - 1;
		st = new StringTokenizer(br.readLine());
		eR = Integer.parseInt(st.nextToken());
		eC = Integer.parseInt(st.nextToken());
		eD = Integer.parseInt(st.nextToken()) - 1;

		Queue<Node> queue = new LinkedList<>();
		visited[sR][sC][sD] = true;
		queue.add(new Node(sR, sC, sD));
		int move = 0;
		while (!queue.isEmpty()) {
			int qSize = queue.size();
			for (int q = 0; q < qSize; q++) {
				Node now = queue.poll();
				if (now.r == eR && now.c == eC && now.d == eD) {
					System.out.println(move);
					return;
				}
				for (int a = 0, d = (now.d & 2) ^ 2; a < 2; a++, d++) {
					if (visited[now.r][now.c][d]) {
						continue;
					}
					visited[now.r][now.c][d] = true;
					queue.add(new Node(now.r, now.c, d));
				}
				for (int dist = 1; dist <= 3; dist++) {
					int nextRow = now.r + dist * dr[now.d];
					int nextCol = now.c + dist * dc[now.d];
					if (!boundaryCheck(nextRow, nextCol)) {
						break;
					}
					if (map[nextRow][nextCol] == 0) {
						break;
					}
					if (visited[nextRow][nextCol][now.d]) {
						continue;
					}
					visited[nextRow][nextCol][now.d] = true;
					queue.add(new Node(nextRow, nextCol, now.d));
				}
			}
			move++;
		}

	}

	public static boolean boundaryCheck(int row, int col) {
		return 1 <= row && row <= N && 1 <= col && col <= M;
	}
}