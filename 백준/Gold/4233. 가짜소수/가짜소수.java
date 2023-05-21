import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static long power(long x, long p, long mod) {
		long res = 1;
		while (p > 0) {
			if ((p & 1) != 0) {
				res *= x;
				res %= mod;
			}
			x *= x;
			x %= mod;
			p >>= 1;
		}
		return res;
	}

	public static boolean check(long p) {
		if (p == 2) {
			return true;
		}
		for (long i = 3; i * i <= p; i += 2) {
			if (p % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long p = Integer.parseInt(st.nextToken());
			long a = Integer.parseInt(st.nextToken());
			if (p == 0) {
				break;
			}
			if (check(p)) {
				sb.append("no\n");
				continue;
			}
			if (power(a, p, p) == a) {
				sb.append("yes\n");
			} else {
				sb.append("no\n");
			}
		}
		System.out.print(sb);
	}
}