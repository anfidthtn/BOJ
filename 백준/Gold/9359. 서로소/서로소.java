import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tNum = 1; tNum <= t; tNum++) {
			sb.append("Case #").append(tNum).append(": ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			long A = Long.parseLong(st.nextToken());
			long B = Long.parseLong(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			List<Integer> primes = new ArrayList<>();
			int num = N;
			for(int i = 2; i * i <= N; i++) {
				if (num % i == 0) {
					primes.add(i);
					while(num % i == 0) {
						num /= i;
					}
				}
			}
			if (num > 1) {
				primes.add(num);
			}
			sb.append(getCount(B, primes, 0, 0, 1l) - getCount(A - 1, primes, 0, 0, 1l)).append("\n");
		}
		System.out.print(sb.toString());
	}
	public static long getCount(long num, List<Integer> primes, int idx, int count, long value) {
		if (primes.size() == idx) {
			if (count % 2 == 0) {
				return num / value;
			}
			else {
				return - num / value;
			}
		}
		long result = 0;
		result += getCount(num, primes, idx + 1, count, value);
		if (value * primes.get(idx) <= num) {
			result += getCount(num, primes, idx + 1, count + 1, value * primes.get(idx));
		}
		return result;
	}
}