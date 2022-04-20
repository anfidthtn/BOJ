import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *P16926_silver2_배열_돌리기_3
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		int N = Integer.parseInt(info[0]);
		int M = Integer.parseInt(info[1]);
		
		int arrSize = Math.max(N,  M);
		int[][] origin = new int[arrSize + 1][arrSize + 1];
		int[][] temp1 = new int[arrSize + 1][arrSize + 1];
		int[][] temp2;
		int temp;
		
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		while (st.hasMoreTokens()) {
			switch(Integer.parseInt(st.nextToken())) {
			case 1:
				UD(origin, temp1, N, M);
				break;
			case 2:
				LR(origin, temp1, N, M);
				break;
			case 3:
				Clock(origin, temp1, N, M);
				temp = N;
				N = M;
				M = temp;
				break;
			case 4:
				RClock(origin, temp1, N, M);
				temp = N;
				N = M;
				M = temp;
				break;
			case 5:
				ClockPart(origin, temp1, N, M);
				break;
			case 6:
				RClockPart(origin, temp1, N, M);
				break;
			}
			temp2 = origin;
			origin = temp1;
			temp1 = temp2;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				sb.append(origin[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
	}
	/**
	 * 함수.. 내부 설명은 그냥 함수이름보고 알아차리면 될 듯
	 */
	public static void UD(int[][] origin, int[][] result, int N, int M) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				result[N + 1 - i][j] = origin[i][j];
			}
		}
	}
	public static void LR(int[][] origin, int[][] result, int N, int M) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				result[i][M + 1 - j] = origin[i][j];
			}
		}
	}
	public static void Clock(int[][] origin, int[][] result, int N, int M) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				result[j][N + 1 - i] = origin[i][j];
			}
		}
	}
	public static void RClock(int[][] origin, int[][] result, int N, int M) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				result[M + 1 - j][i] = origin[i][j];
			}
		}
	}
	public static void ClockPart(int[][] origin, int[][] result, int N, int M) {
		for (int i = 1; i <= N / 2; i++) {
			for (int j = 1; j <= M / 2; j++) {
				// 1을 2로
				result[i][M / 2 + j] = origin[i][j];
			}
		}
		for (int i = 1; i <= N / 2; i++) {
			for (int j = M / 2 + 1; j <= M; j++) {
				// 2를 3으로
				result[N / 2 + i][j] = origin[i][j];
			}
		}
		for (int i = N / 2 + 1; i <= N; i++) {
			for (int j = M / 2 + 1; j <= M; j++) {
				// 3을 4로
				result[i][j - M / 2] = origin[i][j];
			}
		}
		for (int i = N / 2 + 1; i <= N; i++) {
			for (int j = 1; j <= M / 2; j++) {
				// 4를 1로
				result[i - N / 2][j] = origin[i][j];
			}
		}
	}
	public static void RClockPart(int[][] origin, int[][] result, int N, int M) {
		for (int i = 1; i <= N / 2; i++) {
			for (int j = 1; j <= M / 2; j++) {
				// 1을 4로
				result[i + N / 2][j] = origin[i][j];
			}
		}
		for (int i = 1; i <= N / 2; i++) {
			for (int j = M / 2 + 1; j <= M; j++) {
				// 2를 1로
				result[i][j - M / 2] = origin[i][j];
			}
		}
		for (int i = N / 2 + 1; i <= N; i++) {
			for (int j = M / 2 + 1; j <= M; j++) {
				// 3을 2로
				result[i - N / 2][j] = origin[i][j];
			}
		}
		for (int i = N / 2 + 1; i <= N; i++) {
			for (int j = 1; j <= M / 2; j++) {
				// 4를 3으로
				result[i][j + M / 2] = origin[i][j];
			}
		}
	}
}
