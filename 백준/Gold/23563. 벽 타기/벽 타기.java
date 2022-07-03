import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static char[][] map;
	static boolean[][] nearWall;

	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		map = new char[H + 2][W + 2];
		nearWall = new boolean[H + 2][W + 2];
		int[][] visited = new int[H + 2][W + 2];
		int startRow = -1;
		int startCol = -1;
		int endRow = -1;
		int endCol = -1;
		for (int i = 1; i <= H; i++) {
			String str = br.readLine();
			for (int j = 1; j <= W; j++) {
				map[i][j] = str.charAt(j - 1);
				visited[i][j] = Integer.MAX_VALUE;
				switch(map[i][j]) {
				case 'S':
					startRow = i;
					startCol = j;
					map[i][j] = '.';
					break;
				case 'E':
					endRow = i;
					endCol = j;
					map[i][j] = '.';
					break;
				}
			}
		}
		
		for(int i = 1; i <= H; i++) {
			for(int j = 1; j <= W; j++) {
				if (map[i][j] == '#') {
					for(int d = 0; d < 4; d++) {
						nearWall[i + dr[d]][j + dc[d]] = true;
					}
				}
			}
		}
		
		visited[startRow][startCol] = 0;
		int time = 0;
		Queue<Integer[]> queue = new LinkedList<>();
		queue.add(new Integer[] {startRow, startCol});
		Queue<Integer[]> nextqueue;
		while(!queue.isEmpty()) {
			nextqueue = new LinkedList<>();
			while(!queue.isEmpty()) {
				Integer[] now = queue.poll();
				if (now[0] == endRow && now[1] == endCol) {
					System.out.println(time);
					return;
				}
				for(int d = 0; d < 4; d++) {
					int nextRow = now[0] + dr[d];
					int nextCol = now[1] + dc[d];
					if (map[nextRow][nextCol] == '#') {
						continue;
					}
					int plus = 1;
					if (nearWall[now[0]][now[1]] && nearWall[nextRow][nextCol]) {
						plus = 0;
					}
					if (time + plus >= visited[nextRow][nextCol]) {
						continue;
					}
					visited[nextRow][nextCol] = time + plus;
					if (plus == 0) {
						queue.add(new Integer[] {nextRow, nextCol});
					}
					else {
						nextqueue.add(new Integer[] {nextRow, nextCol});						
					}
				}
			}
			queue = nextqueue;
			time++;
		}
		
		
	}
}