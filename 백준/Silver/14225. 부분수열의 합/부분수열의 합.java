import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int[] nums;
	static boolean[] able;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		able = new boolean[2010101];
		check(0, 0);
		for(int i = 1; i <= 2_000_000; i++) {
			if (!able[i]) {
				System.out.println(i);
				return;
			}
		}
	}

	public static void check(int idx, int sum) {
		able[sum] = true;
		if (idx == N) {
			return;
		}
		check(idx + 1, sum);
		check(idx + 1, sum + nums[idx]);
	}
}