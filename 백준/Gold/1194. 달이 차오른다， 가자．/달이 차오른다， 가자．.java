import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int row;
		int col;
		int status;

		public Point() {
			super();
		}

		public Point(int row, int col, int status) {
			super();
			this.row = row;
			this.col = col;
			this.status = status;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, -1, 0, 1};
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N + 2][M + 2];
		boolean[][][] visitStatus = new boolean[N + 2][M + 2][1 << 6];
		Point startPoint = new Point();
		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = line.charAt(j - 1);
				if (map[i][j] == '0') {
					// 민식이 위치 빈 칸
					map[i][j] = '.';
					startPoint.row = i;
					startPoint.col = j;
					// 1 << 0 ~ 1 << 5는 각각 열쇠 획득 여부에 사용
					startPoint.status = 0;
				}
			}
		}
		
		LinkedList<Point> queue = new LinkedList<>();
		queue.add(startPoint);
		visitStatus[startPoint.row][startPoint.col][startPoint.status] = true;
		int moveCount = 0;
		while(!queue.isEmpty()) {
			LinkedList<Point> nextQueue = new LinkedList<>();
			moveCount++;
			while(!queue.isEmpty()) {
				Point now = queue.poll();
				for(int d = 0; d < 4; d++) {
					int nextRow = now.row + dr[d];
					int nextCol = now.col + dc[d];
					int nextStatus = now.status;
					
					// 도착지점을 발견하면 끝낸다.
					if (map[nextRow][nextCol] == '1') {
						System.out.println(moveCount);
						return;
					}
					
					// 열쇠를 발견하면 비트 연산으로 열쇠 보유 상태를 만들어준다.
					if ('a' <= map[nextRow][nextCol] && map[nextRow][nextCol] <= 'f') {
						nextStatus |= (1 << (map[nextRow][nextCol] - 'a'));
					}
					
					// 해당 상태로 방문한 적이 있으면 넘어감
					if (visitStatus[nextRow][nextCol][nextStatus]) continue;
					// 방문 상태를 저장한다.
					visitStatus[nextRow][nextCol][nextStatus] = true;
					
					// 문을 발견하면 비트 연산으로 열쇠 보유 상태를 확인한다.
					if ('A' <= map[nextRow][nextCol] && map[nextRow][nextCol] <= 'F') {
						// 열쇠 보유를 하지 못했으면 넘어간다.
						if ((nextStatus & (1 << (map[nextRow][nextCol] - 'A'))) == 0) continue;
					}
					
					
					switch(map[nextRow][nextCol]) {
					case 0:
					case '#':
						// 테두리거나 벽이면 못 감
						break;
					default:
						nextQueue.add(new Point(nextRow, nextCol, nextStatus));
						break;
					}
				}
			}
			queue = nextQueue;
		}
		
		System.out.println(-1);
	}
	
}