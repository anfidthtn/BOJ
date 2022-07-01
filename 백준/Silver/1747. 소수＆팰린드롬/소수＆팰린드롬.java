import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		N = N == 1 ? 2 : N;
		for(;;N++) {
			if (!same(N)) {
				continue;
			}
			if (!isPrime(N)) {
				continue;
			}
			System.out.println(N);
			return;
		}
	}
	public static boolean isPrime(int num) {
		for(int p = 2; p * p <= num; p++) {
			if (num % p == 0) {
				return false;
			}
		}
		return true;
	}
	public static boolean same(int num) {
		String str = String.valueOf(num);
		int left = 0;
		int right = str.length() - 1;
		while(left < right) {
			if (str.charAt(left) != str.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}
}