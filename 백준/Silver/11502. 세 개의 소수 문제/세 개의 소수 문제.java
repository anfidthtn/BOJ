import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean[] filter = new boolean[1001];
		Arrays.fill(filter, true);
		List<Integer> primes = new ArrayList<>();
		for (int i = 2; i * i <= 1000; i++) {
			if (filter[i]) {
				for (int j = i * i; j <= 1000; j += i) {
					filter[j] = false;
				}
			}
		}
		for (int i = 2; i <= 1000; i++) {
			if (filter[i]) {
				primes.add(i);
			}
		}
		int len = primes.size();
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			int target = Integer.parseInt(br.readLine());
			boolean find = false;
			total: for (int a = 0; a < len; a++) {
				for (int b = a; b < len; b++) {
					for (int c = b; c < len; c++) {
						if (target == primes.get(a) + primes.get(b) + primes.get(c)) {
							find = true;
							sb.append(primes.get(a)).append(" ").append(primes.get(b)).append(" ").append(primes.get(c))
									.append("\n");
							break total;
						}
					}
				}
			}
			if (!find) {
				sb.append("0\n");
			}
		}
		System.out.print(sb.toString());
	}
}