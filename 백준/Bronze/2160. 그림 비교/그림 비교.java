import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[][][] 그림들;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		그림들 = new int[N + 1][5][7];
		for (int i = 1; i <= N; i++) {
			그림입력(br, i);
		}
		int ans = 1 << 30;
		int a = 0;
		int b = 0;
		for (int i = 1; i < N; i++) {
			for(int j = i + 1; j <= N; j++) {
				int 차이 = 차이비교(i, j);
				if (ans > 차이) {
					ans = 차이;
					a = i;
					b = j;
				}
			}
		}
		System.out.print(a + " " + b);
	}
	public static int 차이비교(int a, int b) {
		int ans = 0;
		for (int i = 0; i < 5; i++) {
			for(int j = 0; j < 7; j++) {
				ans += 그림들[a][i][j] ^ 그림들[b][i][j];
			}
		}
		return ans;
	}

	public static void 그림입력(BufferedReader br, int x) throws IOException {
		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			for (int j = 0; j < 7; j++) {
				그림들[x][i][j] = str.charAt(j) == '.' ? 1 : 0;
			}
		}
	}
}