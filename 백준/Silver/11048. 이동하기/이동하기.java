import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] nums = new int[R + 1][C + 1];
		int[][] ans = new int[R + 1][C + 1];
		for (int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= C; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
				ans[i][j] = nums[i][j] + ans[i][j - 1];
				ans[i][j] = Math.max(nums[i][j] + ans[i - 1][j], ans[i][j]);
			}
		}
		System.out.println(ans[R][C]);
	}
}