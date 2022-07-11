import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		List<Long> primes = new ArrayList<>();
		boolean[] isNotPrime = new boolean[1_000_001];
		for(int p = 2; p <= 1_000_000; p++) {
			if (!isNotPrime[p]) {
				primes.add((long)p);
				for(long notP = (long) p * p; notP <= 1_000_000; notP += p) {
					isNotPrime[(int) notP] = true;
				}
			}
		}
		for (int tNum = 1; tNum <= t; tNum++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long n = Long.parseLong(st.nextToken());
			long k = Long.parseLong(st.nextToken());
			long num = k;
			long min = Long.MAX_VALUE;
			for(long p : primes) {
				if (p * p > k) {
					break;
				}
				if (num % p == 0) {
					int count = 0;
					while(num % p == 0) {
						count++;
						num /= p;
					}
					min = Math.min(getMin(n, p, count), min);
				}
			}
			if (num > 1) {
				min = Math.min(getMin(n, num, 1), min);
			}
			sb.append(min).append("\n");
		}
		System.out.print(sb.toString());
	}
	public static long getMin(long n, long p, int count) {
		long sub = 0;
		while(n > 0) {
			sub += n /= p;
		}
		return sub / count;
	}
}