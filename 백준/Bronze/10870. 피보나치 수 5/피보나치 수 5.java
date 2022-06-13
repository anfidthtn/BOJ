import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] fib;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		fib = new int[N + 1];
		System.out.println(getFib(N));
	}
	public static int getFib(int n) {
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return 1;
		}
		if (fib[n] > 0) {
			return fib[n];
		}
		return fib[n] = getFib(n - 1) + getFib(n - 2);
	}
}