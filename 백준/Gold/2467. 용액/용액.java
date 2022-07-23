import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(nums);
		int left = 0;
		int right = N - 1;
		int resultL = 0;
		int resultR = 0;
		int minAbs = 2_000_000_000;
		while(left < right) {
			int temp;
			int abs = temp = nums[left] + nums[right];
			if (abs < 0) {
				abs *= -1;
			}
			if (abs <= minAbs) {
				resultL = left;
				resultR = right;
				minAbs = abs;
			}
			if (temp < 0) {
				left++;
			}
			else {
				right--;
			}
		}
		System.out.println(nums[resultL] + " " + nums[resultR]);
	}
}