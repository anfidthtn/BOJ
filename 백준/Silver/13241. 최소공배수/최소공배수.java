import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		System.out.print(lcm(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
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