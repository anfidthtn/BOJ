import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static boolean[] prime = new boolean[10001];
	static List<Integer> primeList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 2; i <= 10000; i++) {
			prime[i] = true;
		}
		for (int i = 2; i <= 10000; i++) {
			if (prime[i]) {
				primeList.add(i);
				for (int j = i * 2; j <= 10000; j += i) {
					prime[j] = false;
				}
			}
		}
		makeNum(0, 1, N);
		bw.flush();
	}
	public static void makeNum(int now, int digit, int N) throws IOException {
		for(int i = 0; i < 10; i++) {
			int next = now * 10 + i;
			if (!isPrime(next)) {
				continue;
			}
			if (digit == N) {
				bw.write(String.valueOf(next));
				bw.write('\n');
			}
			else {
				makeNum(next, digit + 1, N);
			}
		}
	}

	public static boolean isPrime(int num) {
		if (num <= 1) {
			return false;
		}
		for(int p : primeList) {
			if (num < p * p) {
				break;
			}
			if (num % p == 0) {
				return false;
			}
		}
		return true;
	}
}