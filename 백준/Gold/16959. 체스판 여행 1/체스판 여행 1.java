import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	static class Node {
		/**
		 * 0 : knight
		 * 1 : bishop
		 * 2 : rook
		 */
		int statue;
		int change;
		int time;
		Point point;

		public Node(int statue, int change, int time, Point point) {
			this.statue = statue;
			this.change = change;
			this.time = time;
			this.point = point;
		}
	}

	static class MinNode {
		int minTime;
		int minChange;

		public MinNode() {
			this.minTime = 1 << 20;
			this.minChange = 1 << 20;
		}
	}

	static int N;
	static int[][] map;
	static Point[] points;
	static Point[][] mapPoints;
	static MinNode[][] minNodes;
	
	static int[][] dr = {{ -2, -1, 1, 2, 2, 1, -1, -2}, {-1, 1, 1, -1}, {-1, 0, 1, 0}};
	static int[][] dc = {{ 1, 2, 2, 1, -1, -2, -2, -1}, { 1, 1, -1, -1}, {0, -1, 0, 1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		mapPoints = new Point[N][N];
		points = new Point[N * N + 1];
		minNodes = new MinNode[N * N + 1][3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				points[map[i][j]] = new Point(i, j);
				mapPoints[i][j] = points[map[i][j]];
			}
		}
		for (int i = 0; i < 3; i++) {
			minNodes[1][i] = new MinNode();
			minNodes[1][i].minChange = 0;
			minNodes[1][i].minTime = 0;
		}
		for (int i = 1; i < N * N; i++) {
			Point start = points[i];
			PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					if (o1.time == o2.time) {
						return o1.change - o2.change;
					}
					return o1.time - o2.time;
				}
			});
			MinNode[][][] visited = new MinNode[N][N][3];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					for (int s = 0; s < 3; s++) {
						visited[r][c][s] = new MinNode();
					}
				}
			}
			for (int s = 0; s < 3; s++) {
				visited[start.row][start.col][s] = minNodes[i][s];
				pq.add(new Node(s, minNodes[i][s].minChange, minNodes[i][s].minTime, start));
			}
			while (!pq.isEmpty()) {
				Node now = pq.poll();
				for(int nextS = 0; nextS < 3; nextS++) {
					if (nextS == now.statue) {
						continue;
					}
					pqProcessing(pq, visited, nextS, now.time + 1, now.change + 1, now.point.row, now.point.col);
				}
				switch(now.statue) {
				case 0:
					// knight
					for(int d = 0; d < 8; d++) {
						int nextRow = now.point.row + dr[0][d];
						int nextCol = now.point.col + dc[0][d];
						pqProcessing(pq, visited, now.statue, now.time + 1, now.change, nextRow, nextCol);
					}
					break;
				case 1:
				case 2:
					for(int k = 0; k < 4; k++) {
						for (int d = 1; d <= N; d++) {
							int nextRow = now.point.row + d * dr[now.statue][k];
							int nextCol = now.point.col + d * dc[now.statue][k];
							pqProcessing(pq, visited, now.statue, now.time + 1, now.change, nextRow, nextCol);
						}
					}
					break;
				}
			}
			minNodes[i + 1] = visited[points[i + 1].row][points[i + 1].col];
		}
		int ansTime = 1 << 20;
		int ansChange = 1 << 20;
		for(int s = 0; s < 3; s++) {
			if (minNodes[N * N][s].minTime > ansTime) {
				continue;
			}
			if (minNodes[N * N][s].minTime == ansTime && minNodes[N * N][s].minChange > ansChange) {
				continue;
			}
			ansTime = minNodes[N * N][s].minTime;
			ansChange = minNodes[N * N][s].minChange;
		}
		System.out.print(ansTime);
	}
	public static void pqProcessing(PriorityQueue<Node> pq, MinNode[][][] visited, int nextStatue, int nextTime, int nextChange, int nextRow, int nextCol) {
		if (!boundaryCheck(nextRow, nextCol)) {
			return;
		}
		MinNode nextVisit = visited[nextRow][nextCol][nextStatue];
		if (nextVisit.minTime < nextTime) {
			return;
		}
		if (nextVisit.minTime == nextTime && nextVisit.minChange <= nextChange) {
			return;
		}
		nextVisit.minChange = nextChange;
		nextVisit.minTime = nextTime;
		pq.add(new Node(nextStatue, nextChange, nextTime, mapPoints[nextRow][nextCol]));
	}
	public static boolean boundaryCheck(int row, int col) {
		return 0 <= row && row < N && 0 <= col && col < N;
	}
}