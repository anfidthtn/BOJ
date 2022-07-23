import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		int left = 0;
		int right = N - 1;
		int maxAble = 0;
		while(left < right) {
			int between = right - left - 1;
			if (nums[left] < nums[right]) {
				maxAble = maxAble > nums[left] * between ? maxAble : nums[left] * between;
				left++;
			}
			else {
				maxAble = maxAble > nums[right] * between ? maxAble : nums[right] * between;
				right--;
			}
		}
		System.out.println(maxAble);
	}
}