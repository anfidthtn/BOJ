import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0) {
				return;
			}
			boolean[] primes = new boolean[2 * N + 20];
			Arrays.fill(primes, true);
			primes[0] = false;
			primes[1] = false;
			for(int p = 2; p <= 2 * N; p++) {
				if (primes[p]) {
					for(int px = p * 2; px <= 2 * N; px += p) {
						primes[px] = false;
					}
				}
			}
			int count = 0;
			for(int i = N + 1; i <= 2 * N; i++) {
				if (primes[i]) {
					count++;
				}
			}
			System.out.println(count);
		}
	}
}