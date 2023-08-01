import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		long pre = 0;
		long suf = 0;
		for (int i = 0; i < n; i++) {
			suf += nums[i];
		}
		long ans = 0;
		for (int i = 0; i < n; i++) {
			suf -= nums[i];
			pre += nums[i] * nums[i];
			ans = Math.max(ans, suf * pre);
		}
		System.out.println(ans);

	}
}