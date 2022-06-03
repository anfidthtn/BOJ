import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Node{
		int row;
		int col;
		long time;
		public Node(int row, int col, long time) {
			this.row = row;
			this.col = col;
			this.time = time;
		}
	}
	static final int[] dr = {-1, 0, 1, 0};
	static final int[] dc = {0, 1, 0, -1};
	
	static long totalMin;
	static int W;
	static int H;
	static int[] times;
	static char[][] map;
	static long[][] minTime;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			times = new int[26];
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				times[st.nextToken().charAt(0) - 'A'] = Integer.parseInt(st.nextToken());
			}
			map = new char[H + 2][W + 2];
			minTime = new long[H + 2][W + 2];
			int startRow = -1;
			int startCol = -1;
			for(int i = 1; i <= H; i++) {
				String str = br.readLine();
				for(int j = 1; j <= W; j++) {
					map[i][j] = str.charAt(j - 1);
					minTime[i][j] = Long.MAX_VALUE >> 2;
					if (map[i][j] == 'E') {
						startRow = i;
						startCol = j;
					}
				}
			}
			PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
				@Override
				public int compare(Node o1, Node o2) {
					if (o1.time < o2.time) {
						return -1;
					}
					else if (o1.time == o2.time) {
						return 0;
					}
					else {
						return 1;
					}
				}
			});
			pq.add(new Node(startRow, startCol, 0));
			minTime[startRow][startCol] = 0;
			while(!pq.isEmpty()) {
				Node now = pq.poll();
				if (now.row == 1 || now.row == H || now.col == 1 || now.col == W) {
					sb.append(now.time).append("\n");
					break;
				}
				for(int d = 0; d < 4; d++) {
					int nextRow = now.row + dr[d];
					int nextCol = now.col + dc[d];
					if (now.time + times[map[nextRow][nextCol] - 'A'] >= minTime[nextRow][nextCol]) {
						continue;
					}
					minTime[nextRow][nextCol] = now.time + times[map[nextRow][nextCol] - 'A'];
					pq.add(new Node(nextRow, nextCol, minTime[nextRow][nextCol]));
				}
			}
		}
		System.out.print(sb.toString());
	}
}