import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long answer = 0;
		int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				answer += Math.abs(nums[i] - nums[j]);
			}
		}
		System.out.println(answer);
	}
}