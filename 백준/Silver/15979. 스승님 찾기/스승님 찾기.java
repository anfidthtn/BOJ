import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Math.abs(Integer.parseInt(st.nextToken()));
		int M = Math.abs(Integer.parseInt(st.nextToken()));
		if (N == 0 && M == 0) {
			System.out.println(0);
		}
		else if (gcd(N, M) == 1) {
			System.out.println(1);
		}
		else {
			System.out.println(2);
		}
	}
	public static int gcd(int a, int b) {
		if (a < b) {
			int temp = a;
			a = b;
			b = temp;
		}
		while(b > 0) {
			a = a % b;
			int temp = a;
			a = b;
			b = temp;
		}
		return a;
	}
}