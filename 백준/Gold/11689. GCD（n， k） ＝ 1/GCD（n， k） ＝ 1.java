import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static List<Long> primes;
	static Long total;
	static Long N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Long.parseLong(br.readLine());
		Long num = N;
		total = 0L;
		primes = new ArrayList<>();
		for(Long div = 2L; div * div <= N; div++) {
			if(num % div == 0) {
				primes.add(div);
				while(num % div == 0) {
					num /= div;
				}
			}
		}
		if (num > 1) {
			primes.add(num);
		}
		check(0, 0, 1L);
		System.out.println(total);
	}
	public static void check(int idx, int count, Long value) {
		if (idx == primes.size()) {
			if (count % 2 == 0) {
				total += N / value;
			}
			else {
				total -= N / value;
			}
			return;
		}
		
		check(idx + 1, count, value);
		check(idx + 1, count + 1, value * primes.get(idx));
	}
}