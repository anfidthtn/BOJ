import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		long L = Long.parseLong(st.nextToken());
		if (L % lcm(a, b) != 0) {
			// a, b, c의 최소공배수는 a와 b의 최소공배수로 나누어떨어져야한다.
			System.out.print(-1);
			return;
		}
		long ans = 1;
		long l = L;
		for (long p = 2; p * p <= L; p++) {
			// a, b, L의 각 소인수별 지수를 구한다.
			int aCount = 0;
			int bCount = 0;
			int lCount = 0;
			while (a % p == 0) {
				a /= p;
				aCount++;
			}
			while (b % p == 0) {
				b /= p;
				bCount++;
			}
			while (l % p == 0) {
				l /= p;
				lCount++;
			}
			/**
			 * 예를들어 a에 2^3, b에 2^4가 있을 때
			 * L에 2^4가 있다면 c에는 2가 없어도 되지만
			 * L에 2^5가 있다면 c에는 2^5가 있어야한다.
			 */
			if (aCount < lCount && bCount < lCount) {
				ans *= power(p, lCount);
			}
		}
		System.out.print(ans);
	}

	public static long power(long num, long pow) {
		long ans = 1;
		while (pow > 0) {
			if (pow % 2 == 1) {
				ans *= num;
			}
			num *= num;
			pow >>= 1;
		}
		return ans;
	}

	public static long gcd(long a, long b) {
		while (b > 0) {
			long temp = a % b;
			a = b;
			b = temp;
		}
		return a;
	}

	public static long lcm(long a, long b) {
		return a / gcd(a, b) * b;
	}
}