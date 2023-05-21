import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int N = Integer.parseInt(br.readLine());
			int[] coins = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int M = Integer.parseInt(br.readLine());
			long[][] cases = new long[N + 1][M + 1];
			cases[0][0] = 1;
			for (int i = 0; i < N; i++) {
				int coin = coins[i];
				for (int target = 0; target <= M; target++) {
					for (int amount = 0; target - coin * amount >= 0; amount++) {
						cases[i + 1][target] += cases[i][target - coin * amount];
					}
				}
			}
			sb.append(cases[N][M]).append("\n");
		}
		System.out.print(sb.toString());
	}
}