import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Long K = Long.parseLong(br.readLine());
		
		List<Long> primes = new ArrayList<>();
		Long num = K;
		for(Long p = 2L; p * p <= K; p++) {
			while(num % p == 0) {
				primes.add(p);
				num /= p;
			}
		}
		if (num > 1) {
			primes.add(num);
		}
		System.out.println(primes.size());
		for(Long p : primes) {
			System.out.print(p + " ");
		}
	}
}