import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			long N = Long.parseLong(br.readLine());
			if (N == 0) {
				break;
			}
			long num = N;
			List<Long> primes = new ArrayList<>();
			for (long i = 2; i * i <= N && i <= num; i++) {
				if (num % i == 0) {
					primes.add(i);
					while (num % i == 0) {
						num /= i;
					}
				}
			}
			if (num != 1) {
				primes.add(num);
			}
			long count = 0;
			for (int i = 1; i < 1 << primes.size(); i++) {
				count += counts(N, primes, i);
			}
			System.out.println(N - 1 - count);
		}
	}
	public static long counts(long N, List<Long> primes, int choice) {
		int count = 0;
		long div = 1;
		for(int i = 0; i < primes.size(); i++) {
			if ((choice & (1 << i)) != 0) {
				count++;
				div *= primes.get(i);
			}
		}
		if (count % 2 == 0) {
			return - (N - 1) / div;
		}
		else {
			return (N - 1) / div;
		}
	}
}