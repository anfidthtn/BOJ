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
			int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int max = 0;
			for (int a = 0; a < nums.length; a++) {
				for (int b = a + 1; b < nums.length; b++) {
					max = Math.max(max, getGCD(nums[a], nums[b]));
				}
			}
			sb.append(max).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static int getGCD(int a, int b) {
		while (b > 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}
}