import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(nums);
		int max = 0;
		for (int a = N - 1; a >= 0; a--) {
			for (int b = a; b >= 0; b--) {
				if (nums[a] + nums[b] >= nums[N - 1]) {
					continue;
				}
				if (nums[a] + nums[b] * 2 <= max) {
					continue;
				}
				for (int c = b; c >= 0; c--) {
					if (nums[a] + nums[b] + nums[c] <= max) {
						break;
					}
					if (contains(nums, 0, N - 1, nums[a] + nums[b] + nums[c])) {
						max = nums[a] + nums[b] + nums[c];
						break;
					}
				}
			}
		}
		System.out.println(max);
	}
	public static boolean contains(int[] nums, int left, int right, int target) {
		int mid = (left + right) >> 1;
		if (right - left == 0) {
			if(nums[mid] == target) {
				return true;
			}
			return false;
		}
		if (target <= nums[mid]) {
			return contains(nums, left, mid, target);
		}
		else {
			return contains(nums, mid + 1, right, target);
		}
	}
}