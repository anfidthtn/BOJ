import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static long[][] bests;
	static int[][] powers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		bests = new long[N][N];
		powers = new int[N][N];
		for (int i = 0; i < N; i++) {
			powers[i][i] = Integer.parseInt(br.readLine());
			Arrays.fill(bests[i], 1l << 50);
			bests[i][i] = 0;
		}
		for (int k = 1; k < N; k++) {
			for (int i = 0; i < N - k; i++) {
				for (int j = i; j < k + i; j++) {
					powers[i][k + i] = Math.max(powers[i][k + i], Math.max(powers[i][j], powers[j + 1][k + i]));
					bests[i][k + i] = Math.min(bests[i][k + i],
							bests[i][j] + bests[j + 1][k + i] + Math.abs(powers[i][j] - powers[j + 1][k + i]));
				}
			}
		}
		System.out.println(bests[0][N - 1]);
	}
}