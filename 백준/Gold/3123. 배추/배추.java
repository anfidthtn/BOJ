import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	/**
	 * X : 밭의 가로좌표
	 * Y : 밭의 세로좌표
	 * N : 스프링쿨러 개수
	 * coolers : 스프링쿨러가 설치된 위치
	 * dr : 확인위치
	 * dc : 확인위치
	 * ■□
	 * □■ : 1
	 * ■■
	 * □□ : pi / 6 + r3 / 4
	 * ■□
	 * □□ : pi / 4
	 */
	static int X;
	static int Y;
	static int N;
	static boolean[][] coolers;
	static int pi4 = 0;
	static int pi6 = 0;
	static int r34 = 0;
	static int one = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(br.readLine());

		coolers = new boolean[X + 1][Y + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			coolers[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
		}

		for (int i = 0; i < X; i++) {
			for (int j = 0; j < Y; j++) {
				if (coolers[i][j] && coolers[i + 1][j + 1]) {
					one++;
				} else if (coolers[i + 1][j] && coolers[i][j + 1]) {
					one++;
				} else if (coolers[i][j] && coolers[i][j + 1] || coolers[i + 1][j + 1] && coolers[i][j + 1]
						|| coolers[i + 1][j + 1] && coolers[i + 1][j] || coolers[i][j] && coolers[i + 1][j]) {
					pi6++;
					r34++;
				} else if (coolers[i][j] || coolers[i][j + 1] || coolers[i + 1][j + 1] || coolers[i + 1][j]) {
					pi4++;
				}
			}
		}
		System.out.println(pi4 * Math.PI / 4.0 + pi6 * Math.PI / 6.0 + r34 * Math.pow(3, 0.5) / 4 + one);

	}
}