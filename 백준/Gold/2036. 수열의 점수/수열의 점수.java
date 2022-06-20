import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] nums = new long[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(br.readLine());
		}
		Arrays.sort(nums);
		BigInteger total = new BigInteger("0");
		for (int idx = 0; idx < N && nums[idx] <= 0; idx++) {
			if (idx == N - 1) {
				total = total.add(new BigInteger(Long.toString(nums[idx])));
			}
			else {
				if (nums[idx + 1] <= 0) {
					total = total.add(new BigInteger(Long.toString(nums[idx] * nums[idx + 1])));
					idx++;
				}
				else {
					total = total.add(new BigInteger(Long.toString(nums[idx])));
				}
			}
		}
		for(int idx = N - 1; idx >= 0 && nums[idx] > 0; idx--) {
			if (idx == 0) {
				total = total.add(new BigInteger(Long.toString(nums[idx])));
			}
			else {
				if (nums[idx] > 1 && nums[idx - 1] > 1) {
					total = total.add(new BigInteger(Long.toString(nums[idx] * nums[idx - 1])));
					idx--;
				}
				else {
					total = total.add(new BigInteger(Long.toString(nums[idx])));
				}
			}
		}
		System.out.println(total);
	}
}