import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		int row;
		int col;
		boolean hasGram;
		public Node(int row, int col, boolean hasGram) {
			this.row = row;
			this.col = col;
			this.hasGram = hasGram;
		}
	}
	static char[][] map;
	static boolean[][][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		map = new char[N + 2][M + 2];
		visited = new boolean[N + 2][M + 2][2];
		Queue<Node> queue = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		queue.add(new Node(1, 1, false));
		int count = 0;
		while(!queue.isEmpty()) {
			if (++count > T) {
				break;
			}
			int qSize = queue.size();
			for(int i = 0; i < qSize; i++) {
				Node now = queue.poll();
				for(int d = 0; d < 4; d++) {
					int nextRow = now.row + dr[d];
					int nextCol = now.col + dc[d];
					if (nextRow == N && nextCol == M) {
						System.out.println(count);
						return;
					}
					if (visited[nextRow][nextCol][1]) {
						continue;
					}
					if (!now.hasGram && visited[nextRow][nextCol][0]) {
						continue;
					}
					if (now.hasGram) {
						visited[nextRow][nextCol][1] = true;
					}
					else {
						visited[nextRow][nextCol][0] = true;
					}
					if ((now.hasGram && map[nextRow][nextCol] != 0) || map[nextRow][nextCol] == '2') {
						queue.add(new Node(nextRow, nextCol, true));
					}
					else if (map[nextRow][nextCol] == '0') {
						queue.add(new Node(nextRow, nextCol, now.hasGram));
					}
				}
			}
		}
		System.out.println("Fail");
	}
}