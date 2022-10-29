import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		while(!check(nums));
	}
	public static boolean check(int[] nums) {
		for(int i = 0; i < 4; i++) {
			if (nums[i] > nums[i + 1]) {
				swap(nums, i, i + 1);
			}
		}
		for(int i = 0; i < 4; i++) {
			if (nums[i] > nums[i + 1]) {
				return false;
			}
		}
		return true;
	}
	public static void swap(int[] nums, int a, int b) {
		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
		for(int i = 0; i < 5; i++) {
			System.out.print(nums[i] + " ");
		}
		System.out.println();
	}
}