import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nums = new int[1_000_001];
		int now = 1;
		int count = 0;
		nums[1] = 1;
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0) {
				break;
			}
			if (n < now) {
				System.out.println(nums[n]);
				continue;
			}
			while(now <= n) {
				count++;
				if (check(count)) {
					nums[now++] = count; 
				}
			}
			System.out.println(nums[n]);
		}
	}
	public static boolean check(int num) {
		int valid = 0;
		while(num > 0) {
			if ((valid & (1 << (num % 10))) != 0) {
				return false;
			}
			valid += 1 << (num % 10);
			num /= 10;
		}
		return true;
	}
}