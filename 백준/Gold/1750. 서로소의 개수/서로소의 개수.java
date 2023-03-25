import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] nums;
	static long[][] results;
	static final int MOD = 10_000_003;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine());
		}
		results = new long[N][100_001];
		System.out.print(getResult(0, 0));
	}

	public static long getResult(int idx, int g) {
		if (idx == N) {
			if (g == 1) {
				return 1;
			}
			return 0;
		}
		if (results[idx][g] != 0) {
			return results[idx][g] == -1 ? 0 : results[idx][g];
		}
		long res = 0;
		res += getResult(idx + 1, gcd(g, nums[idx]));
		res += getResult(idx + 1, g);
		res %= MOD;
		if (res == 0) {
			results[idx][g] = -1;
			return 0;
		} else {
			return results[idx][g] = res;
		}
	}

	public static int gcd(int a, int b) {
		while (b > 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}
}