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
			long ans = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] nums = new int[N];
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(nums);
			for (int size = 1; size <= N; size++) {
				long min = 1l << 50;
				for (int start = 0; start < N - size + 1; start++) {
					long temp = 0;
					for (int i = 0; i < size - 1; i++) {
						temp += nums[start + size - 1] - nums[start + i];
					}
					min = Math.min(min, temp);
				}
				ans += min;
			}
			sb.append(ans).append("\n");
		}
		System.out.print(sb.toString());
	}
}