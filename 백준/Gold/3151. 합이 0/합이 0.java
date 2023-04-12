import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] counts = new int[100000];
		int[] nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			counts[10000 + nums[i]]++;
		}
		long ans = 0;
		for (int i = 0; i < N; i++) {
			counts[10000 + nums[i]]--;
			for (int j = 0; j < i; j++) {
				if (nums[i] + nums[j] <= 10000) {
					ans += counts[10000 - nums[i] - nums[j]];
				}
			}
		}
		System.out.println(ans);
	}
}