import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[] nums = new int[N + 1];
		for(int i = 1; i <= N; i++) {
			nums[i] = i;
		}
		boolean[] prime = new boolean[K + 1];
		for(int i = 2; i <= K; i++) {
			prime[i] = true;
		}
		for(int i = 2; i <= K; i++) {
			if (prime[i]) {
				for(int j = i * 2; j <= K; j += i) {
					prime[i] = false;
				}
				for(int num = 1; num <= N; num++) {
					while (nums[num] % i == 0) {
						nums[num] /= i;
					}
				}
			}
		}
		int count = 0;
		for(int i = 1; i <= N; i++) {
			if (nums[i] == 1) {
				count++;
			}
		}
		System.out.println(count);
	}
}