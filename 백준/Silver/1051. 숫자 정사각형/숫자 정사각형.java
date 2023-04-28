import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] nums = new char[N][];
		for (int i = 0; i < N; i++) {
			nums[i] = br.readLine().toCharArray();
		}
		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int t = Math.min(N, M); t > ans; t--) {
					if (i + t >= N || j + t >= M) {
						continue;
					}
					if (nums[i][j] == nums[i + t][j] && nums[i + t][j] == nums[i + t][j + t]
							&& nums[i + t][j + t] == nums[i][j + t]) {
						ans = t;
					}
				}
			}
		}
		System.out.print((ans + 1) * (ans + 1));
	}
}