import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	static class Cube {
		int U = 0;
		int D = 0;
		int F = 0;
		int B = 0;
		int L = 0;
		int R = 0;

		public void spin(int dir) {
			int temp = U;
			switch (dir) {
			case 0:
				// 위로 돌림
				U = F;
				F = D;
				D = B;
				B = temp;
				break;
			case 1:
				// 오른쪽으로 돌림
				U = L;
				L = D;
				D = R;
				R = temp;
				break;
			case 2:
				// 아래로 돌림
				U = B;
				B = D;
				D = F;
				F = temp;
				break;
			case 3:
				U = R;
				R = D;
				D = L;
				L = temp;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int tcase = 0; tcase < 3; tcase++) {

			// 1이 있는 행과 열 (을 밑면으로 처리할 예정)
			int dRow = 0, dCol = 0;
			Cube cube = new Cube();
			int[][] map = new int[8][8];
			for (int i = 1; i <= 6; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= 6; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						dRow = i;
						dCol = j;
					}
				}
			}
			int[] count = new int[1];
			boolean[][] isVisited = new boolean[8][8];
			isVisited[dRow][dCol] = true;
			if (check(cube, map, isVisited, count, dRow, dCol)) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
		}
	}

	public static boolean check(Cube cube, int[][] map, boolean[][] isVisited, int[] count, int row, int col) {
		// 빈 칸은 넘긴다.
		if (map[row][col] == 0)
			return true;
		// 아랫면에 이미 숫자가 적혀있다면 중복방문이 일어나서 불가능한 전개도라는 소리이다.
		if (cube.D == 1)
			return false;
		// 숫자가 적혀있지 않은 면에는 현재 위치에 적힌 숫자를 적는다.
		cube.D = map[row][col];
		count[0]++;
		for (int d = 0; d < 4; d++) {
			// 이미 본 칸은 넘어가고
			if (isVisited[row + dr[d]][col + dc[d]])
				continue;
			// QR등록 하고
			isVisited[row + dr[d]][col + dc[d]] = true;
			// 해당 방향으로 돌린 다음
			cube.spin(d);
			// 만약에 돌린 후에 안되는 전개도로 판명나면 false 반환
			if (!check(cube, map, isVisited, count, row + dr[d], col + dc[d]))
				return false;
			// 해당 방향까지는 되는 전개도라면 원래대로 다시 돌림
			cube.spin((d + 2) % 4);
		}
		return true;
	}
}