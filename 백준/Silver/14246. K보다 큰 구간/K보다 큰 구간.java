import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] nums = new long[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		long ans = 0;
		long K = Integer.parseInt(br.readLine());
		long sum = 0;
		int left = 0;
		int right = -1;
		while (left < N && right < N) {
			if (sum <= K) {
				sum += nums[++right];
			} else {
				ans += N - right;
				sum -= nums[left++];
			}
		}
		System.out.print(ans);
	}
}