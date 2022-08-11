import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static final int MOD = 1_000_000_007;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		int[] leftW = new int[N + 2];
		int[] rightE = new int[N + 2];
		for(int i = 1; i <= N; i++) {
			leftW[i] = leftW[i - 1] + (str.charAt(i - 1) == 'W' ? 1 : 0);
		}
		for(int i = N; i >= 1; i--) {
			rightE[i] = rightE[i + 1] + (str.charAt(i - 1) == 'E' ? 1 : 0);
		}
		long answer = 0;
		for(int i = 1; i <= N; i++) {
			if (rightE[i] < 2) {
				break;
			}
			if (str.charAt(i - 1) == 'H') {
				answer += (long) leftW[i] * (pow(2, rightE[i]) - rightE[i] - 1);
				answer %= MOD;
			}
		}
		if (answer < 0) {
			answer += MOD;
		}
		System.out.print(answer);
	}
	public static long pow(long num, int exp) {
		long answer = 1;
		while(exp > 0) {
			if (exp % 2 == 1) {
				answer *= num;
				answer %= MOD;
			}
			num *= num;
			num %= MOD;
			exp >>= 1;
		}
		return answer;
	}
}