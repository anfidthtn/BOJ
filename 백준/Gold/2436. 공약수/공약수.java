import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		long mul = (long) a * Integer.parseInt(st.nextToken());
		long lastP = a;
		long lastSum = Long.MAX_VALUE;
		for (long p = a; p * p <= mul; p += a) {
			if (mul % p == 0 && (mul / p) % a == 0) {
				if (getGCD(p, mul / p) != a) {
					continue;
				}
				if (p + mul / p < lastSum) {
					lastSum = p + mul / p;
					lastP = p;
				}
			}
		}
		System.out.print(lastP + " " + mul / lastP);
	}
	public static long getGCD(long a, long b) {
		while(b > 0) {
			long temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}
}