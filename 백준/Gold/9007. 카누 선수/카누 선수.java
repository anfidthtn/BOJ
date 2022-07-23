import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int[] nums = new int[N];
			int[][] sums = new int[2][N * N];
			for(int a = 0; a < 2; a++) {
				st = new StringTokenizer(br.readLine());
				for(int i = 0; i < N; i++) {
					nums[i] = Integer.parseInt(st.nextToken());
				}
				int idx = 0;
				st = new StringTokenizer(br.readLine());
				for(int i = 0; i < N; i++) {
					int temp = Integer.parseInt(st.nextToken());
					for(int j = 0; j < N; j++) {
						sums[a][idx++] = temp + nums[j];
					}
				}
				Arrays.sort(sums[a]);
			}
			int left = 0;
			int right = N * N - 1;
			int result = 1_000_000_000;
			int minAbs = 1_000_000_000;
			while(left < N * N && right >= 0) {
				int temp = sums[0][left] + sums[1][right];
				int abs = temp - K;
				if (abs < 0) {
					abs *= -1;
				}
				if (abs < minAbs) {
					minAbs = abs;
					result = temp;
				}
				else if (abs == minAbs){
					result = Math.min(temp, result);
				}
				if (temp < K) {
					left++;
				}
				else {
					right--;
				}
			}
			sb.append(result).append("\n");
		}
		System.out.print(sb.toString());
	}
}