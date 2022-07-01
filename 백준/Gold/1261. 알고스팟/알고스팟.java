import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, -1, 0, 1 };

	static class Node {
		int row;
		int col;
		int count;

		public Node(int row, int col, int count) {
			this.row = row;
			this.col = col;
			this.count = count;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		if (N == 1 && M == 1) {
			System.out.println(0);
			return;
		}
		char[][] map = new char[N + 2][M + 2];
		int[][] distance = new int[N + 2][M + 2];
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = str.charAt(j - 1);
				distance[i][j] = N * M + 1;
			}
		}

		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.count - o2.count;
			}
		});
		pq.add(new Node(1, 1, 0));
		distance[1][1] = 0;

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			for (int d = 0; d < 4; d++) {
				int nextRow = now.row + dr[d];
				int nextCol = now.col + dc[d];
				if (map[nextRow][nextCol] == 0) {
					continue;
				}
				int nextCount = map[nextRow][nextCol] == '1' ? now.count + 1 : now.count;
				if (nextRow == N && nextCol == M) {
					System.out.println(nextCount);
					return;
				}
				if (distance[nextRow][nextCol] <= nextCount) {
					continue;
				}
				distance[nextRow][nextCol] = nextCount;
				pq.add(new Node(nextRow, nextCol, nextCount));
			}
		}
	}
}