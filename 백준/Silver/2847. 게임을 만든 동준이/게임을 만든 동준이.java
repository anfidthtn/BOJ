import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		int answer = 0;
		for(int i = N - 1; i > 0; i--) {
			answer += Math.max(nums[i - 1] - (nums[i] - 1), 0);
			nums[i - 1] -= Math.max(nums[i - 1] - (nums[i] - 1), 0);
		}
		System.out.println(answer);
	}
}