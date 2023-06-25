import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Main {
	static int N;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		System.out.println(getMax(0, N - 1));
	}

	public static int getMax(int left, int right) {
		if (left == right) {
			return nums[left];
		}
		int len = right - left + 1;
		// left select
		int leftGcd = 0;
		for (int i = left; i < left + len / 2; i++) {
			leftGcd = gcd(nums[i], leftGcd);
		}
		int leftAns = leftGcd + getMax(left + len / 2, right);
		int rightGcd = 0;
		for (int i = left + len / 2; i <= right; i++) {
			rightGcd = gcd(nums[i], rightGcd);
		}
		int rightAns = rightGcd + getMax(left, left + len / 2 - 1);
		return Math.max(leftAns, rightAns);
	}

	public static int gcd(int a, int b) {
		while (b > 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}
}