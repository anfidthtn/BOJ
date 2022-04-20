import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K =Integer.parseInt(st.nextToken());
		int[] dp = new int[K + 1];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			for(int j = K - W; j >= 0; j--) {
				dp[j + W] = Math.max(dp[j + W], dp[j] + V);
			}
		}
		System.out.print(dp[K]);
	}
}