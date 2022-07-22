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
		int x = Integer.parseInt(br.readLine());
		
		int count = 0;
		int left = 0;
		int right = N - 1;
		while(left < right) {
			int temp = nums[left] + nums[right];
			if (temp == x) {
				left++;
				right--;
				count++;
			}
			else if (temp < x) {
				left++;
			}
			else {
				right--;
			}
		}
		System.out.println(count);
	}
}