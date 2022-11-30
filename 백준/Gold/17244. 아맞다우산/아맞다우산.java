import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C;

	static class MapNode {
		char data;
		int row, col;
		boolean[] visited;

		public MapNode(char data, int row, int col) {
			this.data = data;
			this.row = row;
			this.col = col;
			this.visited = new boolean[1 << 5];
		}
	}

	static class BFSNode {
		int row, col;
		int visited;

		public BFSNode(int row, int col, int visited) {
			this.row = row;
			this.col = col;
			this.visited = visited;
		}
	}

	static MapNode[][] map;
	static int key;

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new MapNode[R][C];
		int startRow = 0, startCol = 0;
		int xCount = 0;
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = new MapNode(str.charAt(j), i, j);
				switch (map[i][j].data) {
				case 'S':
					startRow = i;
					startCol = j;
					map[i][j].data = '.';
					break;
				case 'X':
					// 문제 조건상 침범할 일이 없어서 ascii 0 ~ 4 써도 된다.
					map[i][j].data = (char) xCount;
					xCount++;
					break;
				}
			}
		}
		key = (1 << xCount) - 1;
		int time = 0;
		Queue<BFSNode> queue = new LinkedList<>();
		queue.add(new BFSNode(startRow, startCol, 0));
		map[startRow][startCol].visited[0] = true;
		while (!queue.isEmpty()) {
			time++;
			int qSize = queue.size();
			for (int q = 0; q < qSize; q++) {
				BFSNode now = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nextRow = now.row + dr[d];
					int nextCol = now.col + dc[d];
					if (!boundaryCheck(nextRow, nextCol)) {
						continue;
					}
					int nextData = map[nextRow][nextCol].data;
					if (nextData == 'E' && now.visited == key) {
						System.out.println(time);
						return;
					}
					if (nextData == '#' || nextData == 'E') {
						continue;
					}
					int nextVisit = now.visited;
					if (map[nextRow][nextCol].data < 5) {
						nextVisit |= (1 << (int) map[nextRow][nextCol].data);
					}
					if (map[nextRow][nextCol].visited[nextVisit]) {
						continue;
					}
					map[nextRow][nextCol].visited[nextVisit] = true;
					queue.add(new BFSNode(nextRow, nextCol, nextVisit));
				}
			}
		}
	}

	public static boolean boundaryCheck(int row, int col) {
		return 0 <= row && row < R && 0 <= col && col < C;
	}
}