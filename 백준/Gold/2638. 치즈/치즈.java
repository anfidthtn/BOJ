import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Cell {
	int r;
	int c;
	boolean isCheese;
	boolean[] adjDir;

	public Cell(boolean isCheese, int r, int c) {
		this.isCheese = isCheese;
		this.r = r;
		this.c = c;
		if (isCheese)
			adjDir = new boolean[4];
	}
	public int getDirCount() {
		int count = 0;
		for(int d = 0; d < 4; d++) {
			if (adjDir[d]) count++;
		}
		return count;
	}
}

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 패딩해서 만들어주고
		Cell[][] map = new Cell[N + 2][M + 2];

		int cheeseCount = 0;

		// 입력 받아서 치즈인지 아닌지로 본다.
		String[] line;
		for (int i = 1; i <= N; i++) {
			line = br.readLine().split(" ");
			for (int j = 1; j <= M; j++) {
				if (line[j - 1].equals("1")) {
					map[i][j] = new Cell(true, i, j);
					cheeseCount++;
				} else {
					map[i][j] = new Cell(false, i, j);
				}
			}
		}
		// 처음부터 치즈가 없는 경우.
		if (cheeseCount == 0) {
			System.out.println(0);
			return;
		}
		// 경과 시간
		int eTime = 0;

		// 외부 공기 중에 방문했는지 여부를 저장하는 2차원 배열
		boolean[][] isVisited = new boolean[N + 2][M + 2];
		Queue<Cell> air = new LinkedList<>();
		Queue<Cell> cheeseAir = new LinkedList<>();
		// 1, 1은 문제 조건에서 빈 칸(바깥공기)임이 보장되어있음.
		isVisited[1][1] = true;
		air.offer(map[1][1]);

		// 큐가 다 빌 때까지 돌린다.
		while (!air.isEmpty()) {
			// 한 번의 시간이 지날 때
			eTime++;
			// 공기의 전파를 시작한다.
			while (!air.isEmpty()) {
				Cell nowAir = air.poll();
				for (int d = 0; d < 4; d++) {
					// 4방향에 대해서 탐색한다.
					int nextRow = nowAir.r + dr[d];
					int nextCol = nowAir.c + dc[d];
					if (isVisited[nextRow][nextCol]) continue;
					Cell nextCell = map[nextRow][nextCol];
					// 테두리면 넘기고
					if (nextCell == null)
						continue;
					if (nextCell.isCheese) {
						// 공기와 치즈가 만나면 치즈가 어느방향의 공기와 만났는지 기록한다.
						nextCell.adjDir[d] = true;
						if (nextCell.getDirCount() >= 2) {
							// 공기와 2면 이상에서 만난 치즈이면 그 치즈를 공기로 바꿔주고 치즈에어 큐에 넣는다.
							nextCell.isCheese = false;
							cheeseCount--;
							if (cheeseCount == 0) {
								System.out.println(eTime);
								return;
							}
							isVisited[nextRow][nextCol] = true;
							cheeseAir.offer(nextCell);
						}
					}
					else {
						isVisited[nextRow][nextCol] = true;
						air.offer(nextCell);
					}
				}
			}
			air.addAll(cheeseAir);
			cheeseAir = new LinkedList<>();
		}
	}
}