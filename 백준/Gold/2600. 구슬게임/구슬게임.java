import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] balls;
	static int[][] results;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		balls = new int[3];
		for (int i = 0; i < 3; i++) {
			balls[i] = Integer.parseInt(st.nextToken());
		}
		results = new int[501][501];
		for(int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			System.out.println(game(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())) == 1 ? 'A' : 'B');
		}
	}
	public static int game(int a, int b) {
		if (a < balls[0] && b < balls[0]) {
			return 2;
		}
		if (results[a][b] != 0) {
			return results[a][b];
		}
		int result = 2;
		for(int i = 0; i < 3; i++) {
			if (a >= balls[i] && game(a - balls[i], b) == 2) {
				result = 1;
			}
			if (b >= balls[i] && game(a, b - balls[i]) == 2) {
				result = 1;
			}
		}
		return results[a][b] = result;
	}
}