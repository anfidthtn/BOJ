import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][][] nums = new int[N + 1][N + 1][11];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				nums[i][j][Integer.parseInt(st.nextToken())]++;
				for(int num = 1; num <= 10; num++) {
					nums[i][j][num] += nums[i - 1][j][num] + nums[i][j - 1][num] - nums[i - 1][j - 1][num];
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		int Q = Integer.parseInt(br.readLine());
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int count = 0;
			for(int num = 1; num <= 10; num++) {
				if (nums[x2][y2][num] - nums[x1 - 1][y2][num] - nums[x2][y1 - 1][num] + nums[x1 - 1][y1 - 1][num] > 0) {
					count++;
				}
			}
			sb.append(count).append("\n");
		}
		System.out.print(sb.toString());
	}
}