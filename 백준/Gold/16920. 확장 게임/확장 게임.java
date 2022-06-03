import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, 1, 0, -1 };

	static class Point {
		int row;
		int col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		int[] Si = new int[P + 1];
		st = new StringTokenizer(br.readLine());

		char[][] map = new char[N + 2][M + 2];
		Queue<Point>[] queues = new LinkedList[P + 1];
		for (int i = 1; i <= P; i++) {
			Si[i] = Integer.parseInt(st.nextToken());
			queues[i] = new LinkedList<>();
		}
		int[] counts = new int[P + 1];
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = str.charAt(j - 1);
				if ('1' <= map[i][j] && map[i][j] <= '9') {
					queues[map[i][j] - '0'].add(new Point(i, j));
					counts[map[i][j] - '0']++;
				}
			}
		}

		while (!isEmpty(queues, P)) {
			for (int p = 1; p <= P; p++) {
				for (int si = 0; si < Si[p] && !queues[p].isEmpty(); si++) {
					int qSize = queues[p].size();
					for(int q = 0; q < qSize; q++) {						
						Point now = queues[p].poll();
						for(int d = 0; d < 4; d++) {
							int nextRow = now.row + dr[d];
							int nextCol = now.col + dc[d];
							if(map[nextRow][nextCol] != '.') {
								continue;
							}
							map[nextRow][nextCol] = (char) p;
							counts[p]++;
							queues[p].add(new Point(nextRow, nextCol));
						}
					}
				}
			}
		}
		
		for(int i = 1; i <= P; i++) {
			System.out.print(counts[i] + " ");
		}
	}
	public static boolean isEmpty(Queue<Point>[] queues, int P) {
		for(int i = 1; i <= P; i++) {
			if (!queues[i].isEmpty()) {
				return false;
			}
		}
		return true;
	}
}