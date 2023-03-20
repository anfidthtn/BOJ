import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int N, M;
	static int[] nums;
	static int ans = -(1 << 20);

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		select(0, 0, false, 0, 0);
		System.out.print(ans);
	}

	public static void select(int idx, int sum, boolean before, int mincount, int count) {
		if (count > M) {
			return;
		}
		if (mincount >= M) {
			ans = Math.max(ans, sum);
		}
		if (idx == N) {
			return;
		}
		select(idx + 1, sum + nums[idx], true, mincount + 1, before ? count : count + 1);
		select(idx + 1, sum, false, mincount, count);
	}
}