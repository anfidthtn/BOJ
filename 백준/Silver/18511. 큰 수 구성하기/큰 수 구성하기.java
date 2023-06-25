import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static long N;
	static int K;
	static int[] nums;
	static long ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		ans = 0;
		check(0);
		System.out.println(ans);
	}

	public static void check(long now) {
		if (now > N) {
			return;
		}
		ans = Math.max(ans, now);
		for (int i = 0; i < K; i++) {
			check(now * 10 + nums[i]);
		}
	}
}