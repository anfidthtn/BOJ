import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Robot {
	// 북쪽, 동쪽, 남쪽, 서쪽 순
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1}; 
	int row;
	int col;
	int face;

	public Robot(int row, int col, int face) {
		super();
		this.row = row;
		this.col = col;
		this.face = face;
	}

	public boolean moveNextPoint(char[][] map) {
		// 4방향에 대해서 탐색
		int nextFace = face;
		for(int i = 0; i < 4; i++) {
			// 만약 이동한다면, 다음으로 바라볼 방향
			// (+ 3) % 4 => 왼쪽으로 회전
			nextFace = (nextFace + 3) % 4;
			if (map[row + dr[nextFace]][col + dc[nextFace]] == 1) {
				// 이번 탐색지점이 빈칸이면
				// 해당 방향으로 방향을 돌린다.
				face = nextFace;
				// 해당 방향으로 간다.
				row += dr[nextFace];
				col += dc[nextFace];
				// 이동했다고 한다.
				return true;
			}
		}
		// 4방향 다 청소하거나 벽인 경우
		// 후진을 못하면 false
		if (map[row + dr[(face + 2) % 4]][col + dc[(face + 2) % 4]] == 0) return false;
		// 후진할 수 있으면 위치이동
		row += dr[(face + 2) % 4];
		col += dc[(face + 2) % 4];
		return true;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		Robot robot = new Robot(Integer.parseInt(st.nextToken()) + 1, Integer.parseInt(st.nextToken()) + 1,
				Integer.parseInt(st.nextToken()));

		// 가장자리 패딩을 위해 2자리씩
		char[][] map = new char[N + 2][M + 2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				// xor비트연산으로 벽을 0 빈칸을 1로 저장
				map[i][j] = (char) (Integer.parseInt(st.nextToken()) ^ 1);
			}
		}
		
		int count = 0;
		// 로봇이 멈출 때까지 동작한다.
		do {
			if (map[robot.row][robot.col] == 1) {
				// 현 위치가 청소하지 않은 곳이면 청소하고 카운트 올린다.
				map[robot.row][robot.col] = 2;
				count++;
			}
			// 못 움직였다면 끝낸다.
		} while (robot.moveNextPoint(map));
		System.out.print(count);
	}
}