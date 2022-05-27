import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] maxCount;
	static int[][] link;
	static final int MOD = 100;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		maxCount = new int[MOD][MOD];
		link = new int[MOD][MOD];
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			link[a % MOD][b % MOD] = 1;
			link[b % MOD][a % MOD] = 1;
		}
		for(int i = MOD - 1; i >= 0; i--) {
			for(int j = i + 1; j < MOD; j++) {
				for(int k = i; k < j; k++) {
					maxCount[i][j] = Math.max(maxCount[i][j], maxCount[i][k] + maxCount[k + 1][j] + link[i][j]);
				}
			}
		}
		System.out.print(maxCount[0][MOD - 1]);
	}
}