import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int ans = 0;
		int now = 0;
		int kill = 0;
		for (int i = 0; i < N; i++) {
			if (now < nums[i]) {
				now = nums[i];
				kill = 0;
			}
			if (now > nums[i]) {
				kill++;
				ans = Math.max(ans, kill);
			}
		}
		System.out.println(ans);
	}
}