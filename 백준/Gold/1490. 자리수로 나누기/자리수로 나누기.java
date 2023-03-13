import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long N = Long.parseLong(br.readLine());
		long num = N;
		long LCM = 1;
		while(num > 0) {
			if (num % 10 != 0) {
				LCM = lcm(LCM, num % 10);
			}
			num /= 10;
		}
		System.out.print(getRes(N, LCM));
	}
	public static long getRes(long N, long LCM) {
		long cover = 1;
		while(true) {
			for(long i = 0; i < cover; i++) {
				if ((N + i) % LCM == 0){
					return N + i;
				}
			}
			N *= 10;
			cover *= 10;
		}
	}
	public static long gcd(long a, long b) {
		while(b > 0) {
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