import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Map<Integer, Integer>> primesCount = new ArrayList<>();
		Map<Integer, Integer> totalCount = new TreeMap<>();
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			Map<Integer, Integer> primes = new TreeMap<>();
			for(int p = 2; p * p <= num; p++) {
				if (num % p == 0) {
					int count = 0;
					while(num % p == 0) {
						count++;
						num /= p;
					}
					primes.put(p, count);
					if (!totalCount.containsKey(p)) {
						totalCount.put(p, 0);
					}
					totalCount.put(p, totalCount.get(p) + count);
				}
			}
			if (num > 1) {
				primes.put(num, 1);
				if (!totalCount.containsKey(num)) {
					totalCount.put(num, 0);
				}
				totalCount.put(num, totalCount.get(num) + 1);
			}
			primesCount.add(primes);
		}
		int score = 1;
		int count = 0;
		for(int p : totalCount.keySet()) {
			int times = totalCount.get(p) / N;
			for(int i = 0; i < times; i++) {
				score *= p;
			}
			if (times > 0) {
				for(Map<Integer, Integer> primes : primesCount) {
					if (!primes.containsKey(p)) {
						count += times;
					}
					else {
						count += Math.max(0, times - primes.get(p));
					}
				}
			}
		}
		System.out.print(score + " " + count);
	}
}