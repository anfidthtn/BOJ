import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		int[] pre = new int[N + 2];
		int[] post = new int[N + 2];
		pre[1] = nums[1];
		post[N] = nums[N];
		for (int i = 2; i <= N; i++) {
			pre[i] = gcd(pre[i - 1], nums[i]);
		}
		for (int i = N - 1; i >= 1; i--) {
			post[i] = gcd(post[i + 1], nums[i]);
		}
		int K = -1;
		int maxGCD = -1;
		for (int i = 1; i <= N; i++) {
			int tempGCD = gcd(pre[i - 1], post[i + 1]);
			if (tempGCD > maxGCD) {
				if (nums[i] % tempGCD != 0) {
					maxGCD = tempGCD;
					K = nums[i];
				}
			}
		}
		if (maxGCD == -1) {
			System.out.println(-1);
		}
		else {
			System.out.println(maxGCD + " " + K);
		}
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