import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static long[] ans;
	static List<Integer> primes;
	static boolean[] isNotPrime;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ans = new long[N + 1];
		ans[0] = 1;
		makeList(N);
		for(int p : primes) {
			for(int i = p; i <= N; i++) {
				ans[i] += ans[i - p];
				ans[i] %= 123456789;
			}
		}
		System.out.println(ans[N]);
	}

	public static void makeList(int N) {
		isNotPrime = new boolean[N + 1];
		primes = new ArrayList<>();
		for (int p = 2; p <= N; p++) {
			if (!isNotPrime[p]) {
				primes.add(p);
				for (int np = p * p; np <= N; np += p) {
					isNotPrime[np] = true;
				}
			}
		}
	}
}