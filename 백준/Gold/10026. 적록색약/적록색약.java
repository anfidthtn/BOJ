import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N + 2][N + 2];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i + 1][j + 1] = s.charAt(j);
			}
		}
		
		int countNO = 0;
		boolean[][] isVisited = new boolean[N + 2][N + 2];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (isVisited[i][j]) continue;
				// 구역수
				countNO++;
				Queue<Integer[]> bfs = new LinkedList<>();
				bfs.offer(new Integer[] {i, j});
				char nowColor = map[i][j];
				while(!bfs.isEmpty()) {
					Integer[] nowPoint = bfs.poll();
					for(int d = 0 ; d < 4; d++) {
						int nextRow = nowPoint[0] + dr[d];
						int nextCol = nowPoint[1] + dc[d];
						// 다음 방문지점이 같은 색상인데 
						if (map[nextRow][nextCol] == nowColor) {
							// 이미 방문한 곳이면 넘어가고
							if(isVisited[nextRow][nextCol]) continue;
							// 방문하지 않은 곳이면 방문체크 해주고 그 지점을 다음 탐색지점 목록에 넣어준다.
							isVisited[nextRow][nextCol] = true;
							bfs.offer(new Integer[] {nextRow, nextCol});
						}
					}
				}
			}
		}
		int countYES = 0;
		isVisited = new boolean[N + 2][N + 2];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (isVisited[i][j]) continue;
				// 구역수
				countYES++;
				Queue<Integer[]> bfs = new LinkedList<>();
				bfs.offer(new Integer[] {i, j});
				char nowColor = map[i][j];
				while(!bfs.isEmpty()) {
					Integer[] nowPoint = bfs.poll();
					for(int d = 0 ; d < 4; d++) {
						int nextRow = nowPoint[0] + dr[d];
						int nextCol = nowPoint[1] + dc[d];
						// 다음 방문지점이 같은 그룹 색상
						if (((nowColor == 'R' || nowColor == 'G') && (map[nextRow][nextCol] == 'R' || map[nextRow][nextCol] == 'G')) ||
								nowColor == 'B' && map[nextRow][nextCol] == 'B') {
							// 이미 방문한 곳이면 넘어가고
							if(isVisited[nextRow][nextCol]) continue;
							// 방문하지 않은 곳이면 방문체크 해주고 그 지점을 다음 탐색지점 목록에 넣어준다.
							isVisited[nextRow][nextCol] = true;
							bfs.offer(new Integer[] {nextRow, nextCol});
						}
					}
				}
			}
		}
		System.out.print(countNO + " " + countYES);
		
	}
}