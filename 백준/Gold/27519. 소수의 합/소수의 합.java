import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	static long[] cases;
	static boolean[] isPrimes;
	static List<Integer> primes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		makePrimes();
		makeCases();
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			sb.append(cases[Integer.parseInt(br.readLine())]).append("\n");
		}
		System.out.print(sb.toString());
	}

	public static void makeCases() {
		cases = new long[100_001];
		cases[0] = 1;
		for (int p : primes) {
			for (int i = p; i <= 100_000; i++) {
				cases[i] += cases[i - p];
				cases[i] %= 1_000_000_007;
			}
		}
		cases[0] = 0;
	}

	public static void makePrimes() {
		isPrimes = new boolean[100_001];
		Arrays.fill(isPrimes, true);
		isPrimes[0] = false;
		isPrimes[1] = false;
		primes = new ArrayList<>();
		for (int p = 2; (long) p * p <= 100_000; p++) {
			if (isPrimes[p]) {
				for (long nonP = (long) p * p; nonP <= 100_000; nonP += p) {
					isPrimes[(int) nonP] = false;
				}
			}
		}
		for (int i = 2; i <= 100_000; i++) {
			if (isPrimes[i]) {
				primes.add(i);
			}
		}
	}
}