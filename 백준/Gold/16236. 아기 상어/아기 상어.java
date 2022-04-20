import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	
	static class Point{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	static class Shark{
		Point point;
		char eat;
		char size;
		public Shark(Point point) {
			this.point = point;
			this.eat = '0';
			this.size = '2';
		}
		public Point getPoint() {
			return point;
		}
		public int getRow() {
			return point.row;
		}
		public int getCol() {
			return point.col;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		// int형 배열로 만들면 입력이 '0'(48) 말고 0으로 패딩값 0과 똑같이 들어오니까 그냥 char형으로 만들고 모든 연산을 char에 맞춰서 함.
		char[][] map = new char[N + 2][N + 2];
		Shark shark = null;
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				/**
				 * 입력 받으면서 shark는 따로 위치등록 하고 해당 위치는 '0'(빈칸)으로 저장, 나머지는 입력받은대로 저장
				 */
				char now = st.nextToken().charAt(0);
				map[i][j] = now;
				if (now == '9') {
					shark = new Shark(new Point(i, j));
					map[i][j] = '0';
				}
			}
		}
		
		// 마지막으로 먹이를 먹은 시간
		int lastTime = 0;
		// 현재 시간
		int nowTime = 0;
		// 먹을 먹이를 찾았는지 여부를 보는 변수다.
		boolean isFound = true;
		// 먹이를 못 찾으면 끝낸다. 먹이를 찾아서 먹은 위치가 초기 위치라고 가정하고 시작한다.
		while(isFound) {
			// 현재 시간을 마지막으로 먹이를 먹은 시간으로 등록한다.
			lastTime = nowTime;
			// 현재 위치부터 bfs를 돌릴 방문 배열을 만든다.
			boolean[][] isVisited = new boolean[N + 2][N + 2];
			// 현재 위치를 넣고 bfs를 돌린다.
			Queue<Point> bfs = new LinkedList<>();
			isVisited[shark.getRow()][shark.getCol()] = true;
			bfs.add(shark.getPoint());
			// 현 위치에서부터 먹이를 찾기 시작한다.
			while(!bfs.isEmpty()) {
				// 1칸 이동할 때마다 시간을 증가시킨다.
				nowTime++;
				// 다음번 시간에 확인할 bfs위치다.
				Queue<Point> nextBfs = new LinkedList<>();
				// 이번 타임에 먹이를 찾았는지 여부다.
				isFound = false;
				// 먹을 놈이 어디에있는지. 우선순위때문에 처음에는 우측, 아래쪽으로 범위 밖의 인덱스를 둔다.
				int eatRow = N + 1;
				int eatCol = N + 1;
				// 이번 시간에 대한 탐색이다.
				while(!bfs.isEmpty()) {
					// 탐색 위치 꺼내오기
					Point nowPoint = bfs.poll();
					// 4방 탐색
					for(int d = 0; d < 4; d++) {
						int nextRow = nowPoint.row + dr[d];
						int nextCol = nowPoint.col + dc[d];
						// 방문한 곳이면 넘어가고
						if (isVisited[nextRow][nextCol]) continue;
						// QR인증 해주실게요
						isVisited[nextRow][nextCol] = true;
						
						// '1' ~ '자기 사이즈 - 1' 까지는 먹을 수 있다.
						if ('1' <= map[nextRow][nextCol] && map[nextRow][nextCol] < shark.size) {
							// 먹이를 찾았다.
							isFound = true;
							// 현재까지 찾은 먹이 위치 중 위쪽 우선 등록한다.
							if (nextRow < eatRow) {
								eatRow = nextRow;
								eatCol = nextCol;
							}
							// 같으면 왼쪽 우선 등록한다.
							else if (nextRow == eatRow) {
								eatCol = Math.min(eatCol, nextCol);
							}
						}
						// 먹이를 찾았으면 다음번 시간까지 볼 필요 없이 이번 시간에 먹을 수 있는 먹이만 다 조사하면 되고
						// 못 찾았을 때 '0'이나 '자기 사이즈'를 만나면 해당 위치를 다음 탐색지점으로 등록할 수는 있다.
						if (!isFound && ('0' == map[nextRow][nextCol] || map[nextRow][nextCol] == shark.size)) {
							nextBfs.add(new Point(nextRow, nextCol));
						}
					}
				}
				// 이번 시간에 먹이를 찾았다면
				if (isFound) {
					// 일단 먹고, 사이즈만큼 먹었다면
					if(++shark.eat >= shark.size) {
						// 먹은 개수 0개로, 사이즈를 1 더해줌
						shark.eat = '0';
						shark.size++;
					}
					// 먹었으니 해당 위치를 빈칸으로 만듦
					map[eatRow][eatCol] = '0';
					// 먹은 위치로 이동시킨다.
					shark.point = new Point(eatRow, eatCol);
					// 이번 시간에 찾았으니, 다음 시간을 볼 필요가 없다.
					break;
				}
				// 이번 시간에 못 찾았으면 다음 시간을 봐야한다.
				bfs = nextBfs;
			}
			// 현재 위치에서 찾았다면 시간을 기록하고(이 while문 위쪽) 찾은 지점으로부터 다시 같은 과정을 반복한다.
		}
		System.out.print(lastTime);
		
	}
}