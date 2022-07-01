import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		final int MAXVALUE = 1_000_000;
		boolean[] isNonPrime = new boolean[MAXVALUE + 1];
		List<Integer> primes = new ArrayList<>();
		boolean[] isOdd = new boolean[MAXVALUE + 1];
		int oddCount = 0;
		for (int p = 2; p <= MAXVALUE; p++) {
			if (!isNonPrime[p]) {
				primes.add(p);
				for (int nonP = p * 2; nonP <= MAXVALUE; nonP += p) {
					isNonPrime[nonP] = true;
				}
			}
		}
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			int value = num;
			for(int idx = 0;; idx++) {
				int p = primes.get(idx);
				if (p * p > num) {
					break;
				}
				if (value % p == 0) {
					int count = 0;
					while(value % p == 0) {
						count ^= 1;
						value /= p;
					}
					if (count == 1) {
						if (isOdd[p]) {
							oddCount--;
							isOdd[p] = false;
						}
						else {
							oddCount++;
							isOdd[p] = true;
						}
					}
				}
			}
			if (value > 1) {
				if (isOdd[value]) {
					oddCount--;
					isOdd[value] = false;
				}
				else {
					oddCount++;
					isOdd[value] = true;
				}
			}
			
			if (oddCount == 0) {
				bw.write("DA\n");
			}
			else {
				bw.write("NE\n");
			}
		}
		bw.flush();
	}
}