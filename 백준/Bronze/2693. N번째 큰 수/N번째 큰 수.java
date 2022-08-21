import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			Arrays.sort(nums);
			sb.append(nums[7]).append("\n");
		}
		System.out.print(sb.toString());
		
	}
}