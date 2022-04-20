import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static class Point{
		int row;
		int col;
		int K;
		public Point(int row, int col, int k) {
			super();
			this.row = row;
			this.col = col;
			K = k;
		}
	}
	
	
	// 이 함수를 쓰지 않으려면 패딩을 해야하는데, 2겹패딩 해야해서 코딩하다 헷갈릴 거 같아서 그냥 이 함수를 만들었다.
	public static boolean boundaryCheck(int W, int H, int row, int col) {
		if (row < 0 || col < 0 || row >= H || col >= W) return false;
		return true;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] drk = {-2, -1, 1, 2, 2, 1, -1, -2};
		int[] dck = {1, 2, 2, 1, -1, -2, -2, -1};
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, -1, 0, 1};
		
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][] isVisited = new int[H][W];
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < W; j++) {
				if(st.nextToken().charAt(0) == '1') {
					// 장애물이 위치한 곳은 그냥 방문 한 것으로 처리해버린다.
					isVisited[i][j] = K + 1;
				}
			}
		}
		
		if (W == 1 && H == 1) {
			System.out.println(0);
			return;
		}
		
		LinkedList<Point> queue = new LinkedList<>();
		// 시작지점 등록
		isVisited[0][0] = K + 1;
		queue.add(new Point(0, 0, K));
		// 총 동작 수
		int moveCount = 0;
		while(!queue.isEmpty()) {
			// 동작 수 추가
			moveCount++;
			// 다음 동작 때 탐색할 지점
			LinkedList<Point> nextQueue = new LinkedList<>();
			while(!queue.isEmpty()) {
				Point now = queue.poll();
				// K 미소모 단순 4방탐색
				for(int d = 0; d < 4; d++) {
					int nextRow = now.row + dr[d];
					int nextCol = now.col + dc[d];
					// 테두리검사
					if (!boundaryCheck(W, H, nextRow, nextCol)) continue;
					// 방문검사
					if (isVisited[nextRow][nextCol] >= now.K + 1) continue;
					isVisited[nextRow][nextCol] = now.K + 1;
					// 도착지점 검사
					if (nextRow == H - 1 && nextCol == W - 1) {
						System.out.println(moveCount);
						return;
					}
					nextQueue.add(new Point(nextRow, nextCol, now.K));
				}
				// K 소모 말방탐색
				if (now.K > 0) {
					for(int d = 0; d < 8; d++) {
						int nextRow = now.row + drk[d];
						int nextCol = now.col + dck[d];
						// 테두리검사
						if (!boundaryCheck(W, H, nextRow, nextCol)) continue;
						// 방문검사
						if (isVisited[nextRow][nextCol] >= now.K) continue;
						isVisited[nextRow][nextCol] = now.K;
						// 도착지점 검사
						if (nextRow == H - 1 && nextCol == W - 1) {
							System.out.println(moveCount);
							return;
						}
						// K를 소모하는 이동이므로 -1해준다.
						nextQueue.add(new Point(nextRow, nextCol, now.K - 1));
					}
				}
			}
			// 다음 탐색지점을 현재 탐색지점으로
			queue = nextQueue;
		}
		// 못 찾았으면 -1
		System.out.println(-1);
	}
}