import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N + 2];
		String str1 = br.readLine();
		String str2 = br.readLine();
		for(int i = 1; i <= N; i++) {
			nums[i] = (str1.charAt(i - 1) - '0') ^ (str2.charAt(i - 1) - '0');
		}
		nums[0] = 2;
		nums[N + 1] = 2;
		int[] nums2 = Arrays.copyOf(nums, N + 2);
		nums2[1] ^= 1;
		nums2[2] ^= 1;
		int answer = Integer.MAX_VALUE;
		int count1 = 0;
		int count2 = 1;
		for(int i = 2; i <= N; i++) {
			if (nums[i - 1] == 1) {
				nums[i - 1] ^= 1;
				nums[i] ^= 1;
				nums[i + 1] ^= 1;
				count1++;
			}
			if (nums2[i - 1] == 1) {
				nums2[i - 1] ^= 1;
				nums2[i] ^= 1;
				nums2[i + 1] ^= 1;
				count2++;
			}
		}
		if (nums[N - 1] + nums[N] == 0) {
			answer = Math.min(answer, count1);
		}
		if (nums2[N - 1] + nums2[N] == 0) {
			answer = Math.min(answer, count2);
		}
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		}
		else {
			System.out.println(answer);
		}
	}
}