import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			br.readLine();
			int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int min = 1000000;
			int max = -1;
			for(int num : nums) {
				min = Math.min(min, num);
				max = Math.max(max, num);
			}
			sb.append(2 * (max - min)).append("\n");
		}
		System.out.print(sb.toString());
	}
}