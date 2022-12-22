import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		final int MAX = 1_300_001;
		boolean[] primes = new boolean[MAX];
		Arrays.fill(primes, true);
		primes[0] = false;
		primes[1] = false;
		for (int p = 2; p * p < MAX; p++) {
			if (primes[p]) {
				for (long np = (long) p * p; np < MAX; np += p) {
					primes[(int) np] = false;
				}
			}
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int num = Integer.parseInt(br.readLine());
			if (primes[num]) {
				sb.append("0\n");
			}
			else {
				int count = 2;
				int now = num - 1;
				while(!primes[now]) {
					count++;
					now--;
				}
				now = num + 1;
				while(!primes[now]) {
					count++;
					now++;
				}
				sb.append(count).append("\n");
			}
		}
		System.out.print(sb.toString());
	}
}