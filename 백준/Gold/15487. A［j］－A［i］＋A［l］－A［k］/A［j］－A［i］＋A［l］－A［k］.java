import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N + 1];
		int[][] anss = new int[4][N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < 4; i++) {
			Arrays.fill(anss[i], -4000000);
		}
		for (int i = 1; i <= N - 3; i++) {
			anss[0][i] = Math.max(anss[0][i - 1], -nums[i]);
		}
		for (int i = 2; i <= N - 2; i++) {
			anss[1][i] = Math.max(anss[1][i - 1], anss[0][i - 1] + nums[i]);
		}
		for (int i = 3; i <= N - 1; i++) {
			anss[2][i] = Math.max(anss[2][i - 1], anss[1][i - 1] - nums[i]);
		}
		for (int i = 4; i <= N; i++) {
			anss[3][i] = Math.max(anss[3][i - 1], anss[2][i - 1] + nums[i]);
		}
		System.out.println(anss[3][N]);
	}
}