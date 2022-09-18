import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] nums;
	static int[] lineSum;
	static int[] leftSum;
	static int[] rightSum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		nums = new int[N][M];
		lineSum = new int[N];
		leftSum = new int[N];
		rightSum = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
				lineSum[i] += nums[i][j];
			}
		}
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int j = 0; j < M; j++) {
				sum += nums[i][j];
				leftSum[i] = Math.max(leftSum[i], sum);
			}
			sum = 0;
			for(int j = M - 1; j >= 0; j--) {
				sum += nums[i][j];
				rightSum[i] = Math.max(rightSum[i], sum);
			}
		}
		int answer = 0;
		for(int i = 0; i < N; i++) {			
			int max = 0;
			int before = 0;
			for (int j = 0; j < M; j++) {
				before = Math.max(0, before + nums[i][j]);
				max = Math.max(max, before);
			}
			answer = Math.max(max, answer);
		}
		for (int a = 0; a < N; a++) {
			for (int b = 0; b < N; b++) {
				if (a == b) {
					continue;
				}
				int max = leftSum[a] + rightSum[b];
				for(int i = 0; i < N; i++) {
					if (i != a && i != b && lineSum[i] > 0) {
						max += lineSum[i];
					}
				}
				answer = Math.max(max, answer);
			}
		}
		System.out.println(answer);
	}
}