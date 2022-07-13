import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] results = new int[N];
		/**
		 * 마지막은 어차피 -1
		 */
		results[N - 1] = -1;
		for (int i = N - 2; i >= 0; i--) {
			if (nums[i] == nums[i + 1]) {
				results[i] = results[i + 1];
			}
			else {
				results[i] = i + 2;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int result : results) {
			sb.append(result).append(" ");
		}
		System.out.println(sb.toString());
	}
}