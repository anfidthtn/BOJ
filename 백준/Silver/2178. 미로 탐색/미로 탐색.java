import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		String[] map = new String[N];
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine();
		}
		boolean[][] visited = new boolean[N][M];
		Queue<Integer[]> queue = new LinkedList<>();
		visited[0][0] = true;
		queue.add(new Integer[] {0, 0});
		int count = 1;
		while(!queue.isEmpty()) {
			count++;
			int size = queue.size();
			for(int i = 0; i < size; i++) {
				Integer[] now = queue.poll();
				for(int d = 0; d < 4; d++) {
					int nextRow = now[0] + dr[d];
					int nextCol = now[1] + dc[d];
					if (!boundaryCheck(nextRow, nextCol)) {
						continue;
					}
					if(visited[nextRow][nextCol]) {
						continue;
					}
					visited[nextRow][nextCol] = true;
					if (map[nextRow].charAt(nextCol) == '0') {
						continue;
					}
					if (nextRow == N - 1 && nextCol == M - 1) {
						System.out.println(count);
						return;
					}
					queue.add(new Integer[] {nextRow, nextCol});
				}
			}
		}
	}
	public static boolean boundaryCheck(int row, int col) {
		if (row < 0 || col < 0 || row >= N || col >= M) {
			return false;
		}
		return true;
	}
}