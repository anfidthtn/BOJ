import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int result = counting(nums, 0, 0, S);
		if (S == 0) {
			result--;
		}
		System.out.println(result);
	}
	public static int counting(int[] nums, int idx, int value, int S) {
		if (idx == nums.length) {
			if (value == S) {
				return 1;
			}
			else {
				return 0;
			}
		}
		int result = 0;
		result += counting(nums, idx + 1, value + nums[idx], S);
		result += counting(nums, idx + 1, value, S);
		return result;
	}
}