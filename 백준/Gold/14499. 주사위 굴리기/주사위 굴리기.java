import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/**
	 * 인덱스 0은 칸 채우는 용도
	 * 1 : 동쪽
	 * 2 : 서쪽
	 * 3 : 북쪽
	 * 4 : 남쪽
	 * 으로 문제 조건에서 주어져서 그렇게 짰음.
	 */
	static int[] dr = {0, 0, 0, -1, 1};
	static int[] dc = {0, 1, -1, 0, 0};
	static class Dice{
		int r;
		int c;
		
		/**
		 * 위아래는 그냥 위아래고
		 * 왼쪽과 오른쪽은 
		 * 열이 감소하는 쪽이 왼쪽, 열이 증가하는 쪽이 오른쪽
		 * 앞쪽과 뒤쪽은
		 * 행이 감소하는 쪽이 뒤쪽, 행이 증가하는쪽이 앞쪽
		 * 배열로 선언해도 되지만, 수가 그~렇게 많진 않고 가독성을 높이기 위해서 각각 개별변수로 선언
		 */
		char U, D, L, R, F, B;

		public Dice(int r, int c) {
			this.r = r;
			this.c = c;
			U = '0';
			D = '0';
			L = '0';
			R = '0';
			F = '0';
			B = '0';
		}
		
		public void move(char[][] map, StringBuilder sb, int d) {
			int nextR = r + dr[d];
			int nextC = c + dc[d];
			// 이동이 불가능한 경우 그냥 이동 종료
			if (map[nextR][nextC] == 0) return;
			// 이동이 가능한 경우 일단 주사위 위치 이동
			r = nextR;
			c = nextC;
			// 이동 방향에 따라 주사위 값의 회전 실행
			char temp;
			switch(d) {
			case 1:
				// 동쪽으로 이동 시, 위, 우측, 아래, 좌측이 시계방향으로 회전
				temp = L;
				L = D;
				D = R;
				R = U;
				U = temp;
				break;
			case 2:
				// 서쪽으로 이동시 동쪽과는 반대로 반시계방향으로 회전
				temp = R;
				R = D;
				D = L;
				L = U;
				U = temp;
				break;
			case 3:
				// 북쪽으로 이동 시 위, 뒤, 아래, 앞이 우측에서 볼 때 시계방향으로 회전
				temp = U;
				U = F;
				F = D;
				D = B;
				B = temp;
				break;
			case 4:
				// 남쪽으로 이동 시 위, 뒤, 아래, 앞이 우측에서 볼 때 반시계방향으로 회전
				temp = U;
				U = B;
				B = D;
				D = F;
				F = temp;
				break;
			}
			// 바닥의 값에 따른 값 복사 or 교환
			if (map[r][c] == '0') map[r][c] = D;
			else {
				D = map[r][c];
				map[r][c] = '0';
			}
			// 윗면의 값 출력
			sb.append(U).append('\n');
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 테두리 패딩을 할 계획이라 좌표값을 1 더해줬다.
		int r = Integer.parseInt(st.nextToken()) + 1;
		int c = Integer.parseInt(st.nextToken()) + 1;
		int K = Integer.parseInt(st.nextToken());
		/**
		 * 문제 조건에 칸에 쓰인 수는 0 ~ 9라고 했어서 굳이 int형으로 받을 필요 없다.
		 * 또한 int형으로 받으면 2차원 배열 기본 초기화 때 테두리에 0과 내부 칸 0을 구분하기 귀찮아진다.
		 * char형으로 받으면 0과 '0'으로 구분이 가능하다.
		 */
		char[][] map = new char[N + 2][M + 2];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		StringBuilder sb = new StringBuilder();
		Dice dice = new Dice(r, c);
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			dice.move(map, sb, Integer.parseInt(st.nextToken()));
		}
		System.out.print(sb);
	}
}